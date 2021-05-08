/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.invitation.entity;

import com.channelsharing.hongqu.oms.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * 运营申请邀请码Entity
 * @author liuhangjun
 * @version 2018-07-15
 */
@Data
public class InvitationCodeOper extends DataEntity<InvitationCodeOper> {

	private static final long serialVersionUID = 1L;
	@Length(min=1, max=5, message="邀请码长度必须介于 1 和 5 之间")
	private String code;		// 邀请码

	private Long userId;		// 使用用户
	
	@Range(min=1, max=10, message="邀请码个数必须介于 1 和 10 之间")
	private Integer codeNumber;  // 邀请码个数


	public InvitationCodeOper() {
		super();
	}

	public InvitationCodeOper(Long id){
		super(id);
	}



}
