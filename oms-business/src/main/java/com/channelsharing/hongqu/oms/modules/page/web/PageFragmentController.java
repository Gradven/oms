/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.page.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsInfo;
import com.channelsharing.hongqu.oms.modules.goods.service.GoodsInfoService;
import com.channelsharing.hongqu.oms.modules.page.entity.PageInfo;
import com.google.common.base.Joiner;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.LoggerFactory;
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
import com.channelsharing.hongqu.oms.common.utils.StringUtils;
import com.channelsharing.hongqu.oms.modules.page.entity.PageFragment;
import com.channelsharing.hongqu.oms.modules.page.service.PageFragmentService;

import java.util.HashMap;
import java.util.List;

/**
 * 页面区块配置Controller
 * @author liuhangjun
 * @version 2018-07-26
 */
@Controller
@RequestMapping(value = "${adminPath}/page/pageFragment")
public class PageFragmentController extends BaseController {

	@Autowired
	private PageFragmentService pageFragmentService;

	@ModelAttribute
	public PageFragment get(@RequestParam(required=false) Long id) {
		PageFragment entity = null;
		if (id != null){
			entity = pageFragmentService.get(id);
		}
		if (entity == null){
			entity = new PageFragment();
		}
		return entity;
	}
	
	@RequiresPermissions("page:pageInfo:view")
	@RequestMapping(value = {"index"})
	public String index(PageInfo pageInfo, Model model) {
		
		return "modules/page/pageFragmentIndex";
	}

	@RequiresPermissions("page:pageFragment:view")
	@RequestMapping(value = {"list", ""})
	public String list(PageFragment pageFragment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PageFragment> page = pageFragmentService.findPage(new Page<PageFragment>(request, response), pageFragment);
		model.addAttribute("page", page);
		return "modules/page/pageFragmentList";
	}


	@RequiresPermissions("page:pageFragment:view")
	@RequestMapping(value = "form")
	public String form(PageFragment pageFragment, Model model) {
		model.addAttribute("pageFragment", pageFragment);
		return "modules/page/pageFragmentForm";
	}

	@RequiresPermissions("page:pageFragment:edit")
	@RequestMapping(value = "save")
	public String save(PageFragment pageFragment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pageFragment)){
			return form(pageFragment, model);
		}
		pageFragmentService.save(pageFragment);
		addMessage(redirectAttributes, "保存页面区块配置成功");
		return "redirect:"+Global.getAdminPath()+"/page/pageFragment/?repage";
	}

	@RequiresPermissions("page:pageFragment:edit")
	@RequestMapping(value = "delete")
	public String delete(PageFragment pageFragment, RedirectAttributes redirectAttributes) {
		pageFragmentService.delete(pageFragment);
		addMessage(redirectAttributes, "删除页面区块配置成功");
		return "redirect:"+Global.getAdminPath()+"/page/pageFragment/?repage";
	}

}
