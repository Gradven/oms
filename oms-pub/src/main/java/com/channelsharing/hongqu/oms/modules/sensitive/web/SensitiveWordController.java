/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.sensitive.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.channelsharing.hongqu.oms.modules.sensitive.service.SensitiveWordService;
import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.web.BaseController;
import com.channelsharing.hongqu.oms.modules.sensitive.entity.SensitiveWord;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 敏感词管理Controller
 * @author Karl
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sensitive/sensitiveWord")
public class SensitiveWordController extends BaseController {

	@Autowired
	private SensitiveWordService sensitiveWordService;

	@ModelAttribute
	public SensitiveWord get(@RequestParam(required=false) Long id) {
		SensitiveWord entity = null;
		if (id != null){
			entity = sensitiveWordService.get(id);
		}
		if (entity == null){
			entity = new SensitiveWord();
		}
		return entity;
	}

	@RequiresPermissions("sensitive:sensitiveWord:view")
	@RequestMapping(value = {"list", ""})
	public String list(SensitiveWord sensitiveWord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SensitiveWord> page = sensitiveWordService.findPage(new Page<SensitiveWord>(request, response), sensitiveWord);
		model.addAttribute("page", page);
		return "modules/sensitive/sensitiveWordList";
	}

	@RequiresPermissions("sensitive:sensitiveWord:view")
	@RequestMapping(value = "form")
	public String form(SensitiveWord sensitiveWord, Model model) {
		model.addAttribute("sensitiveWord", sensitiveWord);
		return "modules/sensitive/sensitiveWordForm";
	}

	@RequiresPermissions("sensitive:sensitiveWord:edit")
	@RequestMapping(value = "save")
	public String save(SensitiveWord sensitiveWord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sensitiveWord)){
			return form(sensitiveWord, model);
		}
		sensitiveWordService.save(sensitiveWord);
		addMessage(redirectAttributes, "保存敏感词管理成功");
		return "redirect:"+ Global.getAdminPath()+"/sensitive/sensitiveWord/?repage";
	}

	@RequiresPermissions("sensitive:sensitiveWord:edit")
	@RequestMapping(value = "delete")
	public String delete(SensitiveWord sensitiveWord, RedirectAttributes redirectAttributes) {
		sensitiveWordService.delete(sensitiveWord);
		addMessage(redirectAttributes, "删除敏感词管理成功");
		return "redirect:"+Global.getAdminPath()+"/sensitive/sensitiveWord/?repage";
	}

}
