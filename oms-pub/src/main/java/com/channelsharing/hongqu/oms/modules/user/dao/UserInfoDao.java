/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.user.dao;

import com.channelsharing.hongqu.oms.common.persistence.CrudDao;
import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;
import com.channelsharing.hongqu.oms.modules.user.entity.UserInfo;

/**
 * 用户信息DAO接口
 * @author Karl
 * @version 2018-06-13
 */
@MyBatisDao
public interface UserInfoDao extends CrudDao<UserInfo> {

}
