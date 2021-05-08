/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.config.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.web.BaseController;
import com.channelsharing.hongqu.oms.modules.config.entity.ConfigParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.channelsharing.hongqu.oms.modules.config.service.ConfigParamService;

/**
 * 系统配置项管理Controller
 * @author Karl
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/config/configParam")
public class ConfigParamController extends BaseController {

	@Autowired
	private ConfigParamService configParamService;

	@ModelAttribute
	public ConfigParam get(@RequestParam(required=false) Long id) {
		ConfigParam entity = null;
		if (id != null){
			entity = configParamService.get(id);
		}
		if (entity == null){
			entity = new ConfigParam();
		}
		return entity;
	}

	@RequiresPermissions("config:configParam:view")
	@RequestMapping(value = {"list", ""})
	public String list(ConfigParam configParam, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ConfigParam> page = configParamService.findPage(new Page<ConfigParam>(request, response), configParam);
		model.addAttribute("page", page);
		return "modules/config/configParamList";
	}

	@RequiresPermissions("config:configParam:view")
	@RequestMapping(value = "form")
	public String form(ConfigParam configParam, Model model) {
		model.addAttribute("configParam", configParam);
		return "modules/config/configParamForm";
	}

	@RequiresPermissions("config:configParam:edit")
	@RequestMapping(value = "save")
	public String save(ConfigParam configParam, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, configParam)){
			return form(configParam, model);
		}
		configParamService.save(configParam);
		addMessage(redirectAttributes, "保存系统配置项管理成功");
		return "redirect:"+ Global.getAdminPath()+"/config/configParam/?repage";
	}

	@RequiresPermissions("config:configParam:edit")
	@RequestMapping(value = "delete")
	public String delete(ConfigParam configParam, RedirectAttributes redirectAttributes) {
		configParamService.delete(configParam);
		addMessage(redirectAttributes, "删除系统配置项管理成功");
		return "redirect:"+Global.getAdminPath()+"/config/configParam/?repage";
	}

}
