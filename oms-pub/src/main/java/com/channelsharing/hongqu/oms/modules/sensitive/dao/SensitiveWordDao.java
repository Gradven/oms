/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.sensitive.dao;

import com.channelsharing.hongqu.oms.modules.sensitive.entity.SensitiveWord;
import com.channelsharing.hongqu.oms.common.persistence.CrudDao;
import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;

/**
 * 敏感词管理DAO接口
 * @author Karl
 * @version 2018-06-12
 */
@MyBatisDao
public interface SensitiveWordDao extends CrudDao<SensitiveWord> {

}
