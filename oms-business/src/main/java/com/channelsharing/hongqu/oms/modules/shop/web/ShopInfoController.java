/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.shop.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.channelsharing.hongqu.oms.common.mapper.JsonMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.web.BaseController;
import com.channelsharing.hongqu.oms.common.utils.StringUtils;
import com.channelsharing.hongqu.oms.modules.shop.entity.ShopInfo;
import com.channelsharing.hongqu.oms.modules.shop.service.ShopInfoService;

import java.util.List;

/**
 * 店铺信息Controller
 * @author Karl
 * @version 2018-08-27
 */
@Controller
@RequestMapping(value = "${adminPath}/shop/shopInfo")
public class ShopInfoController extends BaseController {

	@Autowired
	private ShopInfoService shopInfoService;

	@ModelAttribute
	public ShopInfo get(@RequestParam(required=false) Long id) {
		ShopInfo entity = null;
		if (id != null){
			entity = shopInfoService.get(id);
		}
		if (entity == null){
			entity = new ShopInfo();
		}
		return entity;
	}

	@RequiresPermissions("shop:shopInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopInfo shopInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopInfo> page = shopInfoService.findPage(new Page<ShopInfo>(request, response), shopInfo);
		model.addAttribute("page", page);
		return "modules/shop/shopInfoList";
	}

	@RequiresPermissions("shop:shopInfo:view")
	@RequestMapping(value = "form")
	public String form(ShopInfo shopInfo, Model model) {
		model.addAttribute("shopInfo", shopInfo);
		return "modules/shop/shopInfoForm";
	}

	@RequiresPermissions("shop:shopInfo:edit")
	@RequestMapping(value = "save")
	public String save(ShopInfo shopInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shopInfo)){
			return form(shopInfo, model);
		}
		shopInfoService.save(shopInfo);
		addMessage(redirectAttributes, "保存店铺信息成功");
		return "redirect:"+Global.getAdminPath()+"/shop/shopInfo/?repage";
	}

	@RequiresPermissions("shop:shopInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopInfo shopInfo, RedirectAttributes redirectAttributes) {
		shopInfoService.delete(shopInfo);
		addMessage(redirectAttributes, "删除店铺信息成功");
		return "redirect:"+Global.getAdminPath()+"/shop/shopInfo/?repage";
	}

//	@RequiresPermissions("shop:shopInfo:view")
	@RequestMapping(value = "selectList")
	public String selectList(@RequestParam(required = false) String checkMode, ShopInfo shopInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("checkMode", checkMode);
		Page<ShopInfo> page = shopInfoService.findPage(new Page<ShopInfo>(request, response), shopInfo);
		model.addAttribute("page", page);
		return "modules/shop/shopInfoSelectList";
	}


//	@RequiresPermissions("shop:shopInfo:view")
	@ResponseBody
	@RequestMapping(value = "findByIds")
	public String findByIds(String ids) {
		if(StringUtils.isEmpty(ids)){
			return "";
		}
		List list = shopInfoService.findByIds(ids);
		return JsonMapper.nonDefaultMapper().toJson(list);
	}
}
