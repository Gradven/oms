/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.page.dao;

import com.channelsharing.hongqu.oms.common.persistence.TreeDao;
import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;
import com.channelsharing.hongqu.oms.modules.page.entity.PageInfo;

/**
 * 页面信息DAO接口
 * @author liuhangjun
 * @version 2018-07-26
 */
@MyBatisDao
public interface PageInfoDao extends TreeDao<PageInfo> {

}
