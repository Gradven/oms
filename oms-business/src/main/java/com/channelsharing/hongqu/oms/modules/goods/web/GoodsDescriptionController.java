/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.channelsharing.hongqu.oms.common.web.BaseController;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsDescription;
import com.channelsharing.hongqu.oms.modules.goods.service.GoodsDescriptionService;

/**
 * 商品介绍详情Controller
 * @author Karl
 * @version 2018-06-08
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/goodsDescription")
public class GoodsDescriptionController extends BaseController {

	@Autowired
	private GoodsDescriptionService goodsDescriptionService;

	@ModelAttribute
	public GoodsDescription get(@RequestParam(required=false) Long id) {
		GoodsDescription entity = null;
		if (id != null){
			entity = goodsDescriptionService.get(id);
		}
		if (entity == null){
			entity = new GoodsDescription();
		}
		return entity;
	}

	@RequiresPermissions("goods:goodsDescription:view")
	@RequestMapping(value = {"list", ""})
	public String list(GoodsDescription goodsDescription, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GoodsDescription> page = goodsDescriptionService.findPage(new Page<GoodsDescription>(request, response), goodsDescription);
		model.addAttribute("page", page);
		return "modules/goods/goodsDescriptionList";
	}

	@RequiresPermissions("goods:goodsDescription:view")
	@RequestMapping(value = "form")
	public String form(GoodsDescription goodsDescription, Model model) {
		model.addAttribute("goodsDescription", goodsDescription);
		return "modules/goods/goodsDescriptionForm";
	}

	@RequiresPermissions("goods:goodsDescription:edit")
	@RequestMapping(value = "save")
	public String save(GoodsDescription goodsDescription, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, goodsDescription)){
			return form(goodsDescription, model);
		}
		goodsDescriptionService.save(goodsDescription);
		addMessage(redirectAttributes, "保存商品介绍详情成功");
		return "redirect:"+ Global.getAdminPath()+"/goods/goodsDescription/?repage";
	}

	@RequiresPermissions("goods:goodsDescription:edit")
	@RequestMapping(value = "delete")
	public String delete(GoodsDescription goodsDescription, RedirectAttributes redirectAttributes) {
		goodsDescriptionService.delete(goodsDescription);
		addMessage(redirectAttributes, "删除商品介绍详情成功");
		return "redirect:"+Global.getAdminPath()+"/goods/goodsDescription/?repage";
	}

}
