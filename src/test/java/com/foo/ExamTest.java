package com.foo;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExamTest {

    private static List<String> skuIds;

    /**
     * 构造100个 skuid 作为测试条件
     */
    @BeforeClass
    public static void setUp() {
        skuIds = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            skuIds.add(String.valueOf(i));
        }
    }
    @Test
    public void test(){
        ReturnValueService serviceBean = ServiceBeanFactory.getInstance().getServiceBean(ReturnValueService.class);
        List<ReturnValueVo> returnValueVos =serviceBean .listReturnValueVos(skuIds);
        System.out.println(returnValueVos);
        Assert.assertEquals(returnValueVos.size(),97);
        ArrayList<String> kuIds = new ArrayList<>();
        kuIds.add("11111");
        Assert.assertNull(serviceBean.listReturnValueVos(kuIds));
        kuIds.add("1");
        Assert.assertEquals(serviceBean.listReturnValueVos(kuIds).size(),1);

    }
    @AfterClass
    public static void tearDown() {
        skuIds = null;
    }

}
