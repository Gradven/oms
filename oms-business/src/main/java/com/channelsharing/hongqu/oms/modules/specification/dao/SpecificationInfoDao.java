/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.specification.dao;

import com.channelsharing.hongqu.oms.common.persistence.CrudDao;
import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;
import com.channelsharing.hongqu.oms.modules.specification.entity.SpecificationInfo;

/**
 * 规格维度信息DAO接口
 * @author Karl
 * @version 2018-06-05
 */
@MyBatisDao
public interface SpecificationInfoDao extends CrudDao<SpecificationInfo> {

}
