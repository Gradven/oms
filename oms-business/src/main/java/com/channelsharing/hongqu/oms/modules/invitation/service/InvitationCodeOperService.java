/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.invitation.service;

import java.util.List;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.common.utils.InvitationCodeUtil;
import com.channelsharing.hongqu.oms.modules.invitation.entity.InvitationCodeOper;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.modules.invitation.dao.InvitationCodeOperDao;

/**
 * 运营申请邀请码Service
 * @author liuhangjun
 * @version 2018-07-15
 */
@Service
@Transactional(readOnly = true)
public class InvitationCodeOperService extends CrudService<InvitationCodeOperDao, InvitationCodeOper> {

	public InvitationCodeOper get(Long id) {
		return super.get(id);
	}

	public List<InvitationCodeOper> findList(InvitationCodeOper invitationCodeOper) {
		return super.findList(invitationCodeOper);
	}

	public Page<InvitationCodeOper> findPage(Page<InvitationCodeOper> page, InvitationCodeOper invitationCodeOper) {
		return super.findPage(page, invitationCodeOper);
	}

	@Synchronized
	@Transactional(readOnly = false)
	public void save(InvitationCodeOper invitationCodeOper) {
		
		InvitationCodeOper query = new InvitationCodeOper();
		List<InvitationCodeOper> invitationCodeOperList  = super.dao.findList(query);
		
		String code;
		Long newId = null;
		
		for (int i = 0; i < invitationCodeOper.getCodeNumber(); i ++){
			
			// 运营的邀请码为5位长度
			InvitationCodeUtil invitationCodeUtil = new InvitationCodeUtil(5);
			
			if (invitationCodeOperList.size() == 0 && newId == null){
				newId = 1L;   // 初始值为1
			}else if (newId != null){
				newId = newId + 1;
				
			}else {
				Long id = 0L;
				InvitationCodeOper entity = invitationCodeOperList.get(0);
				id = entity.getId();
				newId = id + 1;
			}
			
			code = invitationCodeUtil.encode(newId.intValue());
			

			invitationCodeOper.setCode(code);
			super.save(invitationCodeOper);
		}
		
		
		
		
	}

	@Transactional(readOnly = false)
	public void delete(InvitationCodeOper invitationCodeOper) {
		super.delete(invitationCodeOper);
	}

}
