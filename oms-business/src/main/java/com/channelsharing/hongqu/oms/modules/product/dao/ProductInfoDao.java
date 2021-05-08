/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.product.dao;

import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;
import com.channelsharing.hongqu.oms.modules.product.entity.ProductInfo;
import com.channelsharing.hongqu.oms.common.persistence.CrudDao;
import org.apache.ibatis.annotations.Param;

/**
 * 产品信息DAO接口
 * @author Karl
 * @version 2018-06-11
 */
@MyBatisDao
public interface ProductInfoDao extends CrudDao<ProductInfo> {
    ProductInfo goodsStatistics(@Param("goodsId") long goodsId);
}
