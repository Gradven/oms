/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.page.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.web.BaseController;
import com.channelsharing.hongqu.oms.common.utils.StringUtils;
import com.channelsharing.hongqu.oms.modules.page.entity.PageInfo;
import com.channelsharing.hongqu.oms.modules.page.service.PageInfoService;

/**
 * 页面信息Controller
 * @author liuhangjun
 * @version 2018-07-26
 */
@Controller
@RequestMapping(value = "${adminPath}/page/pageInfo")
public class PageInfoController extends BaseController {

	@Autowired
	private PageInfoService pageInfoService;

	@ModelAttribute
	public PageInfo get(@RequestParam(required=false) Long id) {
		PageInfo entity = null;
		if (id != null){
			entity = pageInfoService.get(id);
		}
		if (entity == null){
			entity = new PageInfo();
		}
		return entity;
	}

	@RequiresPermissions("page:pageInfo:view")
	@RequestMapping(value = {"index"})
	public String index(PageInfo pageInfo, Model model) {

		return "modules/page/pageInfoIndex";
	}

	@RequiresPermissions("page:pageInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PageInfo pageInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PageInfo> list = pageInfoService.findList(pageInfo);
		model.addAttribute("list", list);
		return "modules/page/pageInfoList";
	}

	@RequiresPermissions("page:pageInfo:view")
	@RequestMapping(value = "form")
	public String form(PageInfo pageInfo, Model model) {
		if (pageInfo.getParent()!=null && pageInfo.getParent().getId() != null){
			pageInfo.setParent(pageInfoService.get(pageInfo.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (pageInfo.getId() == null){
				PageInfo pageInfoChild = new PageInfo();
				pageInfoChild.setParent(new PageInfo(pageInfo.getParent().getId()));
				List<PageInfo> list = pageInfoService.findList(pageInfo);
				if (list.size() > 0){
					pageInfo.setSort(list.get(list.size()-1).getSort());
					if (pageInfo.getSort() != null){
						pageInfo.setSort(pageInfo.getSort() + 30);
					}
				}
			}
		}
		if (pageInfo.getSort() == null){
			pageInfo.setSort(30);
		}
		model.addAttribute("pageInfo", pageInfo);
		return "modules/page/pageInfoForm";
	}

	@RequiresPermissions("page:pageInfo:edit")
	@RequestMapping(value = "save")
	public String save(PageInfo pageInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pageInfo)){
			return form(pageInfo, model);
		}
		pageInfoService.save(pageInfo);
		addMessage(redirectAttributes, "保存页面信息成功");
		return "redirect:"+Global.getAdminPath()+"/page/pageInfo/?repage";
	}

	@RequiresPermissions("page:pageInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PageInfo pageInfo, RedirectAttributes redirectAttributes) {
		pageInfoService.delete(pageInfo);
		addMessage(redirectAttributes, "删除页面信息成功");
		return "redirect:"+Global.getAdminPath()+"/page/pageInfo/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<PageInfo> list = pageInfoService.findList(new PageInfo());
		for (int i=0; i<list.size(); i++){
			PageInfo e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

}
