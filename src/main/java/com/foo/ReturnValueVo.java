package com.foo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReturnValueVo implements Serializable{
    private static final long serialVersionUID = -1473328451285811238L;
    /**
     * SKU id
     */
    private String id;
    /**
     * SKU 名称
     */
    private String name;
    /**
     * 货号
     */
    private String artNo;
    /**
     * itemid
     */
    private String spuId;
    /**
     * 库存数量, 保留小数点后2位
     */
    private BigDecimal totalInventory;

    public ReturnValueVo(){}

    public ReturnValueVo(SkuInfoDTO skuInfoDTO,BigDecimal totalInventory) {
        this.id=skuInfoDTO.getId();
        this.name=skuInfoDTO.getName();
        this.artNo=skuInfoDTO.getArtNo();
        this.spuId=skuInfoDTO.getSpuId();
        this.totalInventory=totalInventory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public BigDecimal getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(BigDecimal totalInventory) {
        this.totalInventory = totalInventory;
    }

    @Override
    public String toString() {
        return "\nReturnValueVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", artNo='" + artNo + '\'' +
                ", spuId='" + spuId + '\'' +
                ", totalInventory=" + totalInventory +
                '}';
    }
}
