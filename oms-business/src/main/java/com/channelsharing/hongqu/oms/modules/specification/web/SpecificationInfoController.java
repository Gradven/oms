/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.specification.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import com.channelsharing.hongqu.oms.modules.specification.entity.SpecificationInfo;
import com.channelsharing.hongqu.oms.modules.specification.service.SpecificationInfoService;

import java.util.List;
import java.util.Map;

/**
 * 规格维度信息Controller
 * @author Karl
 * @version 2018-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/specification/specificationInfo")
public class SpecificationInfoController extends BaseController {

	@Autowired
	private SpecificationInfoService specificationInfoService;

	@ModelAttribute
	public SpecificationInfo get(@RequestParam(required=false) Long id) {
		SpecificationInfo entity = null;
		if (id != null){
			entity = specificationInfoService.get(id);
		}
		if (entity == null){
			entity = new SpecificationInfo();
		}
		return entity;
	}

	@RequiresPermissions("specification:specificationInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(SpecificationInfo specificationInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SpecificationInfo> page = specificationInfoService.findPage(new Page<SpecificationInfo>(request, response), specificationInfo);
		model.addAttribute("page", page);
		return "modules/specification/specificationInfoList";
	}

	@RequiresPermissions("specification:specificationInfo:view")
	@RequestMapping(value = "form")
	public String form(SpecificationInfo specificationInfo, Model model) {
		model.addAttribute("specificationInfo", specificationInfo);
		return "modules/specification/specificationInfoForm";
	}

	@RequiresPermissions("specification:specificationInfo:edit")
	@RequestMapping(value = "save")
	public String save(SpecificationInfo specificationInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, specificationInfo)){
			return form(specificationInfo, model);
		}
		specificationInfoService.save(specificationInfo);
		addMessage(redirectAttributes, "保存规格维度信息成功");
		return "redirect:"+Global.getAdminPath()+"/specification/specificationInfo/?repage";
	}

	@RequiresPermissions("specification:specificationInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(SpecificationInfo specificationInfo, RedirectAttributes redirectAttributes) {
		specificationInfoService.delete(specificationInfo);
		addMessage(redirectAttributes, "删除规格维度信息成功");
		return "redirect:"+Global.getAdminPath()+"/specification/specificationInfo/?repage";
	}



	@RequiresPermissions("specification:specificationInfo:view")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		SpecificationInfo query = new SpecificationInfo();
		if(StringUtils.isNoneEmpty(extId)){
			query.setId(Long.parseLong(extId));
		}
		List<SpecificationInfo> list = specificationInfoService.findList(query);
		for (SpecificationInfo e : list) {
			if (StringUtils.isBlank(extId) || (!extId.equals(e.getId()))) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", "0");
				map.put("pIds", "0,");
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

}
