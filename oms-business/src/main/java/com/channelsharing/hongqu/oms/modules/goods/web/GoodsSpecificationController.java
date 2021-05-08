/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.utils.StringUtils;
import com.channelsharing.hongqu.oms.common.web.BaseController;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsSpecification;
import com.channelsharing.hongqu.oms.modules.goods.service.GoodsSpecificationService;
import com.channelsharing.hongqu.oms.modules.specification.entity.SpecificationInfo;
import com.channelsharing.hongqu.oms.modules.specification.service.SpecificationInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 商品对应规格表值表Controller
 * @author Karl
 * @version 2018-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/goodsSpecification")
public class GoodsSpecificationController extends BaseController {

	@Autowired
	private GoodsSpecificationService goodsSpecificationService;

	@ModelAttribute
	public GoodsSpecification get(@RequestParam(required=false) Long id) {
		GoodsSpecification entity = null;
		if (id != null){
			entity = goodsSpecificationService.get(id);
		}
		if (entity == null){
			entity = new GoodsSpecification();
		}
		return entity;
	}

	@RequiresPermissions("goods:goodsSpecification:view")
	@RequestMapping(value = {"list", ""})
	public String list(GoodsSpecification goodsSpecification, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GoodsSpecification> page = goodsSpecificationService.findPage(new Page<GoodsSpecification>(request, response), goodsSpecification);
		model.addAttribute("page", page);
		return "modules/goods/goodsSpecificationList";
	}


	@RequiresPermissions("goods:goodsSpecification:view")
	@RequestMapping(value = "form")
	public String form(GoodsSpecification goodsSpecification, Model model) {
		model.addAttribute("goodsSpecification", goodsSpecification);
		return "modules/goods/goodsSpecificationForm";
	}


	@Resource
	private SpecificationInfoService specificationInfoService;
	private void bindData(GoodsSpecification goodsSpecification) {
		String specificationId = goodsSpecification.getSpecificationId();
		if(StringUtils.isNotEmpty(specificationId)){
			SpecificationInfo specificationInfo = specificationInfoService.get(Long.parseLong(specificationId));
			goodsSpecification.setSpecificationName(specificationInfo.getName());
		}
	}

	@RequiresPermissions("goods:goodsSpecification:edit")
	@RequestMapping(value = "save")
	public String save(GoodsSpecification goodsSpecification, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, goodsSpecification)){
			return form(goodsSpecification, model);
		}
		goodsSpecificationService.save(goodsSpecification);
		addMessage(redirectAttributes, "保存商品对应规格表值表成功");
		return "redirect:"+ Global.getAdminPath()+"/goods/goodsSpecification/?repage";
	}

	@RequiresPermissions("goods:goodsSpecification:edit")
	@RequestMapping(value = "delete")
	public String delete(GoodsSpecification goodsSpecification, RedirectAttributes redirectAttributes) {
		goodsSpecificationService.delete(goodsSpecification);
		addMessage(redirectAttributes, "删除商品对应规格表值表成功");
		return "redirect:"+Global.getAdminPath()+"/goods/goodsSpecification/?repage";
	}

}
