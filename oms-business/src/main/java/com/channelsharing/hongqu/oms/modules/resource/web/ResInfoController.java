/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.resource.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.modules.resource.entity.ResInfo;
import com.channelsharing.hongqu.oms.utils.FileManager;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.channelsharing.hongqu.oms.common.config.Global;
import com.channelsharing.hongqu.oms.common.web.BaseController;
import com.channelsharing.hongqu.oms.modules.resource.service.ResInfoService;

import java.io.IOException;

/**
 * 图片资源管理Controller
 *
 * @author Karl
 * @version 2018-06-08
 */
@Controller
@RequestMapping(value = "${adminPath}/resource/resInfo")
public class ResInfoController extends BaseController {

    @Autowired
    private ResInfoService resInfoService;

    @ModelAttribute
    public ResInfo get(@RequestParam(required = false) Long id) {
        ResInfo entity = null;
        if (id != null) {
            entity = resInfoService.get(id);
        }
        if (entity == null) {
            entity = new ResInfo();
        }
        return entity;
    }

    @RequiresPermissions("resource:resInfo:view")
    @RequestMapping(value = {"list", ""})
    public String list(ResInfo resInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
       if(request.getParameterMap().containsKey("selectEnable")){
           model.addAttribute("selectEnable", true);
       }
        Page<ResInfo> page = resInfoService.findPage(new Page<ResInfo>(request, response), resInfo);
        model.addAttribute("page", page);
        return "modules/resource/resInfoList";
    }

    @RequiresPermissions("resource:resInfo:view")
    @RequestMapping(value = "form")
    public String form(ResInfo resInfo, Model model) {
        model.addAttribute("resInfo", resInfo);
        return "modules/resource/resInfoForm";
    }

    @RequestMapping(value = "imageUpload")
    public void imageUpload(HttpServletRequest request, HttpServletResponse response) {
        try {
            FileManager.ckeditor(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresPermissions("resource:resInfo:edit")
    @RequestMapping(value = "save")
    public String save(ResInfo resInfo, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, resInfo)) {
            return form(resInfo, model);
        }
        resInfoService.save(resInfo);
        addMessage(redirectAttributes, "保存图片资源管理成功");
        return "redirect:" + Global.getAdminPath() + "/resource/resInfo/?repage";
    }

    @RequiresPermissions("resource:resInfo:edit")
    @RequestMapping(value = "delete")
    public String delete(ResInfo resInfo, RedirectAttributes redirectAttributes) {
        resInfoService.delete(resInfo);
        addMessage(redirectAttributes, "删除图片资源管理成功");
        return "redirect:" + Global.getAdminPath() + "/resource/resInfo/?repage";
    }

}
