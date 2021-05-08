/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.recommend.web;

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
import com.channelsharing.hongqu.oms.common.utils.StringUtils;
import com.channelsharing.hongqu.oms.modules.recommend.entity.RecommendSentence;
import com.channelsharing.hongqu.oms.modules.recommend.service.RecommendSentenceService;

/**
 * 商品推荐语Controller
 * @author liuhangjun
 * @version 2018-07-23
 */
@Controller
@RequestMapping(value = "${adminPath}/recommend/recommendSentence")
public class RecommendSentenceController extends BaseController {

	@Autowired
	private RecommendSentenceService recommendSentenceService;

	@ModelAttribute
	public RecommendSentence get(@RequestParam(required=false) Long id) {
		RecommendSentence entity = null;
		if (id != null){
			entity = recommendSentenceService.get(id);
		}
		if (entity == null){
			entity = new RecommendSentence();
		}
		return entity;
	}

	@RequiresPermissions("recommend:recommendSentence:view")
	@RequestMapping(value = {"list", ""})
	public String list(RecommendSentence recommendSentence, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RecommendSentence> page = recommendSentenceService.findPage(new Page<RecommendSentence>(request, response), recommendSentence);
		model.addAttribute("page", page);
		return "modules/recommend/recommendSentenceList";
	}

	@RequiresPermissions("recommend:recommendSentence:view")
	@RequestMapping(value = "form")
	public String form(RecommendSentence recommendSentence, Model model) {
		model.addAttribute("recommendSentence", recommendSentence);
		return "modules/recommend/recommendSentenceForm";
	}

	@RequiresPermissions("recommend:recommendSentence:edit")
	@RequestMapping(value = "save")
	public String save(RecommendSentence recommendSentence, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, recommendSentence)){
			return form(recommendSentence, model);
		}
		recommendSentenceService.save(recommendSentence);
		addMessage(redirectAttributes, "保存商品推荐语成功");
		return "redirect:"+Global.getAdminPath()+"/recommend/recommendSentence/?repage";
	}

	@RequiresPermissions("recommend:recommendSentence:edit")
	@RequestMapping(value = "delete")
	public String delete(RecommendSentence recommendSentence, RedirectAttributes redirectAttributes) {
		recommendSentenceService.delete(recommendSentence);
		addMessage(redirectAttributes, "删除商品推荐语成功");
		return "redirect:"+Global.getAdminPath()+"/recommend/recommendSentence/?repage";
	}

}
