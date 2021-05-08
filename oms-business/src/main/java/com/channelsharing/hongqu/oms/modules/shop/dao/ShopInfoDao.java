/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.shop.dao;

import com.channelsharing.hongqu.oms.common.persistence.CrudDao;
import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;
import com.channelsharing.hongqu.oms.modules.shop.entity.ShopInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺信息DAO接口
 * @author Karl
 * @version 2018-08-27
 */
@MyBatisDao
public interface ShopInfoDao extends CrudDao<ShopInfo> {
    List<ShopInfo> findByIds(@Param("ids") String ids);
}
