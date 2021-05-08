/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.product.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsSpecification;
import com.channelsharing.hongqu.oms.modules.goods.service.GoodsSpecificationService;
import com.channelsharing.hongqu.oms.modules.product.entity.ProductInfo;
import com.channelsharing.hongqu.oms.common.web.BaseController;
import com.channelsharing.hongqu.oms.modules.product.service.ProductInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 产品信息Controller
 * @author Karl
 * @version 2018-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/product/productInfo")
public class ProductInfoController extends BaseController {

	@Autowired
	private ProductInfoService productInfoService;

	@Resource
	private GoodsSpecificationService goodsSpecificationService;

	@ModelAttribute
	public ProductInfo get(@RequestParam(required=false) Long id) {
		ProductInfo entity = null;
		if (id != null){
			entity = productInfoService.get(id);
		}
		if (entity == null){
			entity = new ProductInfo();
		}
		return entity;
	}

	@RequiresPermissions("product:productInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProductInfo productInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProductInfo> page = productInfoService.findPage(new Page<ProductInfo>(request, response), productInfo);
		if(page!=null && page.getList()!=null && page.getList().size()>0){
			HashSet<String> idsSet = new HashSet<>();
			for(ProductInfo product:page.getList()){
				String[] idsAry = product.getGoodsSpecificationIds().split(",");
				for(String id:idsAry){
					idsSet.add(id);
				}
			}

			List<GoodsSpecification> nameList =  goodsSpecificationService.findByIds(idsSet);
			HashMap<String, String> specsMap = new HashMap<>();
			for(GoodsSpecification specs:nameList){
				specsMap.put(specs.getId().toString(),specs.getSpecificationName());
			}

			for(ProductInfo product:page.getList()){
				String[] idsAry = product.getGoodsSpecificationIds().split(",");
				StringBuilder builder = new StringBuilder();
				for(String id: idsAry){
					builder.append(specsMap.get(id));
					builder.append("  ");
				}
				product.setGoodsSpecificationIds(builder.toString());
			}
		}
		model.addAttribute("page", page);
		return "modules/product/productInfoList";
	}

	@RequiresPermissions("product:productInfo:view")
	@RequestMapping(value = "form")
	public String form(ProductInfo productInfo, Model model) {
		model.addAttribute("productInfo", productInfo);
		return "modules/product/productInfoForm";
	}

	@RequiresPermissions("product:productInfo:edit")
	@RequestMapping(value = "save")
	public String save(ProductInfo productInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, productInfo)){
			return form(productInfo, model);
		}
		productInfoService.save(productInfo);
		addMessage(redirectAttributes, "保存产品信息成功");
		return "redirect:"+ Global.getAdminPath()+"/product/productInfo/?repage";
	}

	@RequiresPermissions("product:productInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(ProductInfo productInfo, RedirectAttributes redirectAttributes) {
		productInfoService.delete(productInfo);
		addMessage(redirectAttributes, "删除产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/product/productInfo/?repage";
	}

}
