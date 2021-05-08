/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.sys.dao;

import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;
import com.channelsharing.hongqu.oms.modules.sys.entity.Log;
import com.channelsharing.hongqu.oms.common.persistence.CrudDao;

/**
 * 日志DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
