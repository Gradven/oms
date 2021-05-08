/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.supplier.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.modules.supplier.entity.SupplierUser;
import com.channelsharing.hongqu.oms.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.channelsharing.hongqu.oms.modules.supplier.service.SupplierUserService;

/**
 * 供应商运营用户Controller
 * @author Karl
 * @version 2018-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/supplier/supplierUser")
public class SupplierUserController extends BaseController {

	@Autowired
	private SupplierUserService supplierUserService;

	@ModelAttribute
	public SupplierUser get(@RequestParam(required=false) Long id) {
		SupplierUser entity = null;
		if (id != null){
			entity = supplierUserService.get(id);
		}
		if (entity == null){
			entity = new SupplierUser();
		}
		return entity;
	}

	@RequiresPermissions("supplier:supplierUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(SupplierUser supplierUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SupplierUser> page = supplierUserService.findPage(new Page<SupplierUser>(request, response), supplierUser);
		model.addAttribute("page", page);
		return "modules/supplier/supplierUserList";
	}

	@RequiresPermissions("supplier:supplierUser:view")
	@RequestMapping(value = "form")
	public String form(SupplierUser supplierUser, Model model) {
		model.addAttribute("supplierUser", supplierUser);
		return "modules/supplier/supplierUserForm";
	}

	@RequiresPermissions("supplier:supplierUser:edit")
	@RequestMapping(value = "save")
	public String save(SupplierUser supplierUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, supplierUser)){
			return form(supplierUser, model);
		}
		supplierUserService.save(supplierUser);
		addMessage(redirectAttributes, "保存供应商运营用户成功");
		return "redirect:"+ Global.getAdminPath()+"/supplier/supplierUser/?repage";
	}

	@RequiresPermissions("supplier:supplierUser:edit")
	@RequestMapping(value = "delete")
	public String delete(SupplierUser supplierUser, RedirectAttributes redirectAttributes) {
		supplierUserService.delete(supplierUser);
		addMessage(redirectAttributes, "删除供应商运营用户成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/supplierUser/?repage";
	}

}
