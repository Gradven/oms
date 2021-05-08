/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.enums.BooleanEnum;
import com.channelsharing.hongqu.oms.common.mapper.JsonMapper;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.utils.StringUtils;
import com.channelsharing.hongqu.oms.common.web.BaseController;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsCategory;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsInfo;
import com.channelsharing.hongqu.oms.modules.goods.service.GoodsDescriptionService;
import com.channelsharing.hongqu.oms.modules.goods.service.GoodsSpecificationService;
import com.channelsharing.hongqu.oms.modules.product.entity.ProductInfo;
import com.channelsharing.hongqu.oms.modules.product.service.ProductInfoService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsDescription;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsSpecification;
import com.channelsharing.hongqu.oms.modules.goods.service.GoodsCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.channelsharing.hongqu.oms.modules.goods.service.GoodsInfoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 商品信息Controller
 *
 * @author Karl
 * @version 2018-06-07
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/goodsInfo")
public class GoodsInfoController extends BaseController {

    @Autowired
    private GoodsInfoService goodsInfoService;

    @ModelAttribute
    public GoodsInfo get(@RequestParam(required = false) Long id) {
        GoodsInfo entity = null;
        if (id != null) {
            entity = goodsInfoService.get(id);
        }
        if (entity == null) {
            entity = new GoodsInfo();
        }
        return entity;
    }

    @RequiresPermissions("goods:goodsInfo:view")
    @RequestMapping(value = {"list", ""})
    public String list(GoodsInfo goodsInfo, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<GoodsInfo> page = goodsInfoService.findPage(new Page<GoodsInfo>(request, response), goodsInfo);
//        List<GoodsInfo> goodsInfoList = page.getList();
//        if(goodsInfoList!=null && goodsInfoList.size()>0){
//            for(GoodsInfo goods:goodsInfoList){
//                goods.setGoodsStatistics(productInfoService.goodsStatistics(goods.getId()));
//            }
//        }
        model.addAttribute("page", page);

        return "modules/goods/goodsInfoList";
    }

    @Resource
    private GoodsCategoryService categoryService;

    @Resource
    private GoodsDescriptionService descriptionService;

    @RequiresPermissions("goods:goodsInfo:view")
    @RequestMapping(value = "form")
    public String form(GoodsInfo goodsInfo, Model model) {
        GoodsCategory goodsCategory = categoryService.get(goodsInfo.getCategoryId());
        GoodsDescription description = descriptionService.get(goodsInfo.getId());
        if (description != null && "0".equals(description.getDelFlag())) {
            goodsInfo.setDescription(description);
        }
        goodsInfo.setCategoryName(goodsCategory != null ? goodsCategory.getName() : "请选择");
        ProductInfo statistics = productInfoService.goodsStatistics(goodsInfo.getId());
        if(statistics!=null){ //这几项是统计出来的
//            goodsInfo.setRetailPrice(statistics.getRetailPrice());
//            goodsInfo.setUnitPrice(statistics.getUnitPrice());
            goodsInfo.setStoreNumber(statistics.getStoreNumber());
//            goodsInfo.setProfit(statistics.getProfit());
        }
        model.addAttribute("goodsInfo", goodsInfo);
        return "modules/goods/goodsInfoForm";
    }


    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private GoodsSpecificationService goodsSpecificationService;


    @RequiresPermissions("goods:goodsInfo:view")
    @RequestMapping(value = "productionProfitForm")
    public String productionProfitForm(GoodsInfo goodsInfo, Model model) {

        ProductInfo query = new ProductInfo();
        query.setGoodsId(goodsInfo.getId());
        List<ProductInfo> products = productInfoService.findList(query);

        if (null != products && !products.isEmpty()) {
            for (ProductInfo productInfo : products) {
                String idsStr = productInfo.getGoodsSpecificationIds();
                if (StringUtils.isNotEmpty(idsStr)) {
                    GoodsSpecification specQuery = new GoodsSpecification();
                    specQuery.setSpecificationIds(idsStr);
                    List<GoodsSpecification> specs = goodsSpecificationService.findList(specQuery);
                    StringBuilder builder = new StringBuilder();
                    if (null != specs && !specs.isEmpty()) {
                        for (GoodsSpecification spec : specs) {
                            builder.append(spec.getSpecificationName());
                            builder.append(":");
                            builder.append(spec.getValue());
                            builder.append("   ");
                        }
                    }
                    productInfo.setGoodsSpecificationDes(builder.toString());
                }
            }

            goodsInfo.setRelativeProducts(products);
        }


        model.addAttribute("goodsInfo", goodsInfo);
        return "modules/goods/productionProfitForm";
    }


    @RequiresPermissions("goods:goodsInfo:edit")
    @RequestMapping(value = "saveProfit")
    public String saveProductProfit(GoodsInfo goodsInfo, Model model, RedirectAttributes redirectAttributes) {
        for (ProductInfo productInfo : goodsInfo.getRelativeProducts()) {
            ProductInfo item = productInfoService.get(productInfo.getId());
            if (null != item) {
                item.setProfit(productInfo.getProfit());
                productInfoService.save(item);
            }

        }
        addMessage(redirectAttributes, "关联产品利润配置成功");
        return "redirect:" + Global.getAdminPath() + "/goods/goodsInfo/?repage";
    }


    @RequiresPermissions("goods:goodsInfo:edit")
    @RequestMapping(value = "save")
    public String save(GoodsInfo goodsInfo, Model model, RedirectAttributes redirectAttributes) throws IOException {  //, @RequestParam("coverImgFile") final MultipartFile coverImgFile

        if (!beanValidator(model, goodsInfo)) {
            return form(goodsInfo, model);
        }

        goodsInfoService.save(goodsInfo);
        addMessage(redirectAttributes, "保存商品信息成功");
        return "redirect:" + Global.getAdminPath() + "/goods/goodsInfo/?repage";
    }

    @RequiresPermissions("goods:goodsInfo:edit")
    @RequestMapping(value = "delete")
    public String delete(GoodsInfo goodsInfo, RedirectAttributes redirectAttributes) {

        GoodsInfo goodsInfoResult = goodsInfoService.get(goodsInfo.getId());
        Integer onSaleFlag = 0;
        if (goodsInfoResult != null) {
            onSaleFlag = goodsInfoResult.getOnSaleFlag();
        }

        if (onSaleFlag.equals(BooleanEnum.yes.getCode())) {
            addMessage(redirectAttributes, "删除商品前，请下架商品");
        } else {
            goodsInfoService.delete(goodsInfo);
            addMessage(redirectAttributes, "删除商品信息成功");
        }

        return "redirect:" + Global.getAdminPath() + "/goods/goodsInfo/?repage";
    }

    @RequiresPermissions("goods:goodsInfo:view")
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        GoodsInfo query = new GoodsInfo();
        if (StringUtils.isNoneEmpty(extId)) {
            query.setId(Long.parseLong(extId));
        }
        List<GoodsInfo> list = goodsInfoService.findList(query);
        for (GoodsInfo e : list) {
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



    @RequiresPermissions("goods:goodsInfo:view")
    @RequestMapping(value = "selectList")
    public String selectList(@RequestParam(required = false) String checkMode, GoodsInfo goodsInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("checkMode", checkMode);
        list(goodsInfo, request, response, model);
        return "modules/goods/goodsInfoSelectList";
    }


    @RequiresPermissions("goods:goodsInfo:view")
    @ResponseBody
    @RequestMapping(value = "findByIds")
    public String findByIds(String ids) {
        if(StringUtils.isEmpty(ids)){
            return "";
        }
        List list = goodsInfoService.findByIds(ids);
        return JsonMapper.nonDefaultMapper().toJson(list);
    }


}
