/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.dao;

import com.channelsharing.hongqu.oms.common.persistence.CrudDao;
import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品信息DAO接口
 * @author Karl
 * @version 2018-06-07
 */
@MyBatisDao
public interface GoodsInfoDao extends CrudDao<GoodsInfo> {
    List<GoodsInfo> findByIds(@Param("ids") String ids);
}
