/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.dao;

import com.channelsharing.hongqu.oms.common.persistence.TreeDao;
import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类DAO接口
 * @author Karl
 * @version 2018-06-05
 */
@MyBatisDao
public interface GoodsCategoryDao extends TreeDao<GoodsCategory> {

    List<GoodsCategory> findByIds(@Param("ids") String ids);
}
