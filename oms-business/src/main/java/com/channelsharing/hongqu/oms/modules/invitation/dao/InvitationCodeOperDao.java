/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.invitation.dao;

import com.channelsharing.hongqu.oms.modules.invitation.entity.InvitationCodeOper;
import com.channelsharing.hongqu.oms.common.persistence.CrudDao;
import com.channelsharing.hongqu.oms.common.persistence.annotation.MyBatisDao;

/**
 * 运营申请邀请码DAO接口
 * @author liuhangjun
 * @version 2018-07-15
 */
@MyBatisDao
public interface InvitationCodeOperDao extends CrudDao<InvitationCodeOper> {

}
