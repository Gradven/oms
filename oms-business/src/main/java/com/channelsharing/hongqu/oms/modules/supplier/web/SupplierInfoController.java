/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.supplier.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.web.BaseController;
import com.channelsharing.hongqu.oms.modules.supplier.service.SupplierInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.channelsharing.hongqu.oms.modules.supplier.entity.SupplierInfo;

/**
 * 供应商信息Controller
 * @author Karl
 * @version 2018-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/supplier/supplierInfo")
public class SupplierInfoController extends BaseController {

	@Autowired
	private SupplierInfoService supplierInfoService;

	@ModelAttribute
	public SupplierInfo get(@RequestParam(required=false) Long id) {
		SupplierInfo entity = null;
		if (id != null){
			entity = supplierInfoService.get(id);
		}
		if (entity == null){
			entity = new SupplierInfo();
		}
		return entity;
	}

	@RequiresPermissions("supplier:supplierInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(SupplierInfo supplierInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SupplierInfo> page = supplierInfoService.findPage(new Page<SupplierInfo>(request, response), supplierInfo);
		model.addAttribute("page", page);
		return "modules/supplier/supplierInfoList";
	}

	@RequiresPermissions("supplier:supplierInfo:view")
	@RequestMapping(value = "form")
	public String form(SupplierInfo supplierInfo, Model model) {
		model.addAttribute("supplierInfo", supplierInfo);
		return "modules/supplier/supplierInfoForm";
	}

	@RequiresPermissions("supplier:supplierInfo:edit")
	@RequestMapping(value = "save")
	public String save(SupplierInfo supplierInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, supplierInfo)){
			return form(supplierInfo, model);
		}
		supplierInfoService.save(supplierInfo);
		addMessage(redirectAttributes, "保存供应商信息成功");
		return "redirect:"+ Global.getAdminPath()+"/supplier/supplierInfo/?repage";
	}

	@RequiresPermissions("supplier:supplierInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(SupplierInfo supplierInfo, RedirectAttributes redirectAttributes) {
		supplierInfoService.delete(supplierInfo);
		addMessage(redirectAttributes, "删除供应商信息成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/supplierInfo/?repage";
	}

}
