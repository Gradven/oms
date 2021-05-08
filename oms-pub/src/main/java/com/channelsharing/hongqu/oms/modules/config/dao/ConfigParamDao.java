/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.config.dao;

import com.channelsharing.hongqu.oms.modules.config.entity.ConfigParam;
import com.channelsharing.hongqu.oms.common.persistence.CrudDao;
import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;

/**
 * 系统配置项管理DAO接口
 * @author Karl
 * @version 2018-06-12
 */
@MyBatisDao
public interface ConfigParamDao extends CrudDao<ConfigParam> {

}
