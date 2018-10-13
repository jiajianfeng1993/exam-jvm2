package com.foo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ReturnValueServiceImpl implements ReturnValueService {
    SkuService skuService;
    InventoryService inventoryService;

    public ReturnValueServiceImpl(SkuService skuService,InventoryService inventoryService){
        this.skuService=skuService;
        this.inventoryService=inventoryService;
    }
    /**
     * 得到返回结果
     * @param skuIds
     * @return
     */
    @Override
    public List<ReturnValueVo> listReturnValueVos(List<String> skuIds) {
        //空校验判断
        if(null==skuIds||skuIds.size()==0){
            return null;
        }
        //参数校验
        if(skuIds.size()>100){
            throw new IllegalArgumentException("超出最大查询数量100");
        }
        List<ReturnValueVo> returnValueVos = skuIds.stream().map(skuId -> {
            SkuInfoDTO skuInfoDTO = getBySkuId(skuId);
            if (skuInfoDTO == null) {
                return null;
            }
            return new ReturnValueVo(skuInfoDTO, getTotalInventory(skuId));
        }).collect(Collectors.toList());
        returnValueVos.removeIf(Objects::isNull);
        if(returnValueVos.size()==0){
            return null;
        }
        return returnValueVos;
    }
    /**
     * 获取库存数量
     * @param skuId
     * @return
     */
    private BigDecimal getTotalInventory(String skuId) {
        List<ChannelInventoryDTO> inventoryDTOS = inventoryService.getBySkuId(skuId);
        if(Objects.isNull(inventoryDTOS)||inventoryDTOS.size()==0){
            return new BigDecimal(0);
        }
        return inventoryDTOS.stream().map(ChannelInventoryDTO::getInventory).reduce(ReturnValueServiceImpl::sum).get();
    }

    /**
     * 根据skuId获取DTO
     * @param skuId
     * @return
     */
    private SkuInfoDTO getBySkuId(String skuId){
        List<String> tempIds=new ArrayList<>();
        tempIds.add(skuId);
        List<SkuInfoDTO> infoDTOS = skuService.findByIds(tempIds);
        if(null!=infoDTOS&&infoDTOS.size()>0){
            return infoDTOS.get(0);
        }
        return null;
    }
    private static BigDecimal sum(BigDecimal x,BigDecimal y){
        if(x==null){
            x=new BigDecimal(0);
        }
        if(y==null){
            y=new BigDecimal(0);
        }
        return x.add(y);

    }
}
