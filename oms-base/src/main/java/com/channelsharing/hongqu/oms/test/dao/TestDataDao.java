/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.test.dao;

import com.channelsharing.hongqu.oms.common.persistence.CrudDao;
import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;
import com.channelsharing.hongqu.oms.test.entity.TestData;

/**
 * 单表生成DAO接口
 * @author ThinkGem
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestDataDao extends CrudDao<TestData> {

}
