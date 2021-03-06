# exam-jvm2
java机试题目

## 注意!!!
* 该题目只为了解各位日常实际工作中的编码习惯, 请按自己在工作中接到一个真实需求的方式去完成
* 请使用邮件回复提交, 在提交代码时附带自己的简历, 已方便我们精确的匹配到您
* 务必不要使用 fork 和 pull-request 模式, 因为您这样提交的代码是公开的, 有可能导致不诚信行为!!!

## 需求 (设计一个 Service API, 并实现)

传入一组 SKUId(入参限定不能超过100个), 根据特定[聚合规则](#聚合规则)(见下文), 输出一组 item, 并包含总库存. 

产品给的 prd 要求页面上显示列表如下:

ITEM商品名称|货号|SPU ID|全渠道库存汇总
---|---|---|---
测试商品1|xyz1| |230
测试商品2| |10001|0

### 聚合规则
对于 SKU 类型为原始商品(ORIGIN)的, 按货号聚合成 ITEM; 对于 SKU 类型为数字化商品(DIGITAL)的, 按 SPUId 聚合成 ITEM

### 注意事项
* ITEM商品名称可以取组内任意一个 SKU 的商品名称
* 如果输入了不存在的 SKUId, 后端的服务不会抛异常, 批量接口会有几个返回几个, 单条接口会返回空
* 为了依赖的后端服务 mock 起来比较方便, 测试时 SKUId 请使用从1到100的字符串, 我们在 ExamTest 中提供了 setup 方法来生成输入条件

### 已有服务(接口说明见接口注释)

我们提供了一个服务工厂 `ServiceBeanFactory` 供大家参考以下服务的样例返回值。

* 获取 SKU 基本信息
* 获取 SKU 库存信息(注意库存是分渠道的, 返回值里要把这些渠道库存累加)
* 获取 SKU 价格

## 附录

### 名词解释

SKU：参考 [维基百科](https://zh.wikipedia.org/wiki/%E6%9C%80%E5%B0%8F%E5%AD%98%E8%B4%A7%E5%8D%95%E4%BD%8D)

Item：面向用户展示的一款商品，跟SKU最主要的差别可以看成是一个Item是由该款下面的所有SKU组合而成的。例如，iPhoneX是一个Item，下面分别有： 64G 白色，256G 白色，64G 黑色，256G 黑色，四个SKU。每个SKU可以有自己的库存和价格。当一个Item下面的SKU的价格不一样的时候，这个Item就产生了区间价。
