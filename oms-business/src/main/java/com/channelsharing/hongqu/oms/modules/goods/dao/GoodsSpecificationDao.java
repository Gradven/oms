/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.dao;

import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsSpecification;
import com.channelsharing.hongqu.oms.common.persistence.CrudDao;
import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品对应规格表值表DAO接口
 * @author Karl
 * @version 2018-06-06
 */
@MyBatisDao
public interface GoodsSpecificationDao extends CrudDao<GoodsSpecification> {
    List<GoodsSpecification> findByIds(GoodsSpecification specification);
}
