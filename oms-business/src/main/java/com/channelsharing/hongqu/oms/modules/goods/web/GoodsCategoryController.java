/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.mapper.JsonMapper;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.utils.StringUtils;
import com.channelsharing.hongqu.oms.common.utils.storage.CloudStorageFactory;
import com.channelsharing.hongqu.oms.common.web.BaseController;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsInfo;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.channelsharing.hongqu.oms.common.utils.IdGen;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsCategory;
import com.channelsharing.hongqu.oms.modules.goods.service.GoodsCategoryService;

/**
 * 商品分类Controller
 *
 * @author Karl
 * @version 2018-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/goodsCategory")
public class GoodsCategoryController extends BaseController {

	@Autowired
	private GoodsCategoryService goodsCategoryService;

	@ModelAttribute
	public GoodsCategory get(@RequestParam(required = false) Long id) {
		GoodsCategory entity = null;
		if (id != null) {
			entity = goodsCategoryService.get(id);
		}
		if (entity == null) {
			entity = new GoodsCategory();
		}
		return entity;
	}

	@RequiresPermissions("goods:goodsCategory:view")
	@RequestMapping(value = { "index" })
	public String index(GoodsCategory goodsCategory, Model model) {

		return "modules/goods/goodsCategoryIndex";
	}

	@RequiresPermissions("goods:goodsCategory:view")
	@RequestMapping(value = { "list", "" })
	public String list(GoodsCategory goodsCategory, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		List<GoodsCategory> list = goodsCategoryService.findList(goodsCategory);
		model.addAttribute("list", list);

		return "modules/goods/goodsCategoryList";
	}

	@RequiresPermissions("goods:goodsCategory:view")
	@RequestMapping(value = "form")
	public String form(GoodsCategory goodsCategory, Model model) {
		if (goodsCategory.getParent() != null && goodsCategory.getParent().getId() != null) {
			goodsCategory.setParent(goodsCategoryService.get(goodsCategory.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (goodsCategory.getId() == null) {
				GoodsCategory goodsCategoryChild = new GoodsCategory();
				goodsCategoryChild.setParent(new GoodsCategory(goodsCategory.getParent().getId()));
				List<GoodsCategory> list = goodsCategoryService.findList(goodsCategory);
				if (list.size() > 0) {
					goodsCategory.setSort(list.get(list.size() - 1).getSort());
					if (goodsCategory.getSort() != null) {
						goodsCategory.setSort(goodsCategory.getSort() + 30);
					}
				}
			}
		}
		if (goodsCategory.getSort() == null) {
			goodsCategory.setSort(30);
		}
		model.addAttribute("goodsCategory", goodsCategory);
		return "modules/goods/goodsCategoryForm";
	}

	@RequiresPermissions("goods:goodsCategory:edit")
	@RequestMapping(value = "save")
	public String save(GoodsCategory goodsCategory, Model model, RedirectAttributes redirectAttributes,
			MultipartFile picFile) throws IOException {
		if (!beanValidator(model, goodsCategory)) {
			return form(goodsCategory, model);
		}

		if (picFile != null && org.apache.commons.lang3.StringUtils.startsWith(picFile.getContentType(), "image")) {
			String key = "file/" + DateTime.now().toString("yyyyMMdd") + "/" + IdGen.uuid() + "."
					+ FilenameUtils.getExtension(picFile.getOriginalFilename());
			String picUrl = CloudStorageFactory.getCloudStorage().uploadFile("", picFile.getInputStream(), key);
			goodsCategory.setPicUrl(picUrl);
		}

		goodsCategoryService.save(goodsCategory);
		addMessage(redirectAttributes, "保存查看成功");
		return "redirect:" + Global.getAdminPath() + "/goods/goodsCategory/?repage";
	}

	@RequiresPermissions("goods:goodsCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(GoodsCategory goodsCategory, RedirectAttributes redirectAttributes) {
		goodsCategoryService.delete(goodsCategory);
		addMessage(redirectAttributes, "删除查看成功");
		return "redirect:" + Global.getAdminPath() + "/goods/goodsCategory/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<GoodsCategory> list = goodsCategoryService.findList(new GoodsCategory());
		for (int i = 0; i < list.size(); i++) {
			GoodsCategory e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId().toString())
					&& e.getParentIds().indexOf("," + extId + ",") == -1)) {
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


	@RequiresPermissions("goods:goodsCategory:view")
	@RequestMapping(value = "selectList")
	public String selectList(@RequestParam(required = false) String checkMode, GoodsCategory goodsCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("checkMode", checkMode);
//		list(goodsCategory, request, response, model);
		Page<GoodsCategory> page = goodsCategoryService.findPage(new Page<GoodsCategory>(request, response), goodsCategory);
		model.addAttribute("page", page);
		return "modules/goods/goodsCategorySelectList";
	}


	@RequiresPermissions("goods:goodsCategory:view")
	@ResponseBody
	@RequestMapping(value = "findByIds")
	public String findByIds(String ids) {
		if(StringUtils.isEmpty(ids)){
			return "";
		}
		List list = goodsCategoryService.findByIds(ids);
		return JsonMapper.nonDefaultMapper().toJson(list);
	}

}
