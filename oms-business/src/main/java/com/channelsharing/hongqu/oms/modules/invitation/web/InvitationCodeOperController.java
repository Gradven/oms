/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.invitation.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.web.BaseController;
import com.channelsharing.hongqu.oms.modules.invitation.entity.InvitationCodeOper;
import com.channelsharing.hongqu.oms.modules.invitation.service.InvitationCodeOperService;

/**
 * 运营申请邀请码Controller
 * @author liuhangjun
 * @version 2018-07-15
 */
@Controller
@RequestMapping(value = "${adminPath}/invitation/invitationCodeOper")
public class InvitationCodeOperController extends BaseController {

	@Autowired
	private InvitationCodeOperService invitationCodeOperService;

	@ModelAttribute
	public InvitationCodeOper get(@RequestParam(required=false) Long id) {
		InvitationCodeOper entity = null;
		if (id != null){
			entity = invitationCodeOperService.get(id);
		}
		if (entity == null){
			entity = new InvitationCodeOper();
		}
		return entity;
	}

	@RequiresPermissions("invitation:invitationCodeOper:view")
	@RequestMapping(value = {"list", ""})
	public String list(InvitationCodeOper invitationCodeOper, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<InvitationCodeOper> page = invitationCodeOperService.findPage(new Page<InvitationCodeOper>(request, response), invitationCodeOper);
		model.addAttribute("page", page);
		return "modules/invitation/invitationCodeOperList";
	}

	@RequiresPermissions("invitation:invitationCodeOper:view")
	@RequestMapping(value = "form")
	public String form(InvitationCodeOper invitationCodeOper, Model model) {
		model.addAttribute("invitationCodeOper", invitationCodeOper);
		return "modules/invitation/invitationCodeOperForm";
	}

	@RequiresPermissions("invitation:invitationCodeOper:edit")
	@RequestMapping(value = "save")
	public String save(InvitationCodeOper invitationCodeOper, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, invitationCodeOper)){
			return form(invitationCodeOper, model);
		}
		invitationCodeOperService.save(invitationCodeOper);
		addMessage(redirectAttributes, "保存运营申请邀请码成功");
		return "redirect:"+Global.getAdminPath()+"/invitation/invitationCodeOper/?repage";
	}

	@RequiresPermissions("invitation:invitationCodeOper:edit")
	@RequestMapping(value = "delete")
	public String delete(InvitationCodeOper invitationCodeOper, RedirectAttributes redirectAttributes) {
		invitationCodeOperService.delete(invitationCodeOper);
		addMessage(redirectAttributes, "删除运营申请邀请码成功");
		return "redirect:"+Global.getAdminPath()+"/invitation/invitationCodeOper/?repage";
	}

}
