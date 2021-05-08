package com.channelsharing.hongqu.oms.utils;

import com.channelsharing.hongqu.oms.common.utils.storage.CloudStorageFactory;
import com.channelsharing.hongqu.oms.common.utils.DateUtils;
import com.channelsharing.hongqu.oms.common.utils.IdGen;
import org.apache.commons.io.FilenameUtils;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class FileManager {

    /**
     * 上传文件到云服务器
     *
     * @throws IOException
     */
    public static String uploadFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && multipartFile.getSize() > 0) {
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename()); // 获取文件扩展名
            String key = new StringBuilder()
                    .append(extension)
                    .append("/")
                    .append(DateUtils.getShortDate())
                    .append("/")
                    .append(IdGen.uuid())
                    .append(".")
                    .append(extension)
                    .toString();
            String uri = CloudStorageFactory.getCloudStorage().uploadFile("", multipartFile.getInputStream(), key);
            return uri;
        }

        return null;
    }


    public static Model verifyFileFormat(Model model, MultipartFile... multipartFiles) {
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile != null && multipartFile.getSize() > 0) {
                String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename()); // 获取文件扩展名
                if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(extension, "jpg")
                        || org.apache.commons.lang3.StringUtils.equalsIgnoreCase(extension, "png")
                        || org.apache.commons.lang3.StringUtils.equalsIgnoreCase(extension, "jpeg")) {
                    int maxFileSize = 10 * 1024 * 1024; // 10M
                    if (multipartFile.getSize() > maxFileSize) {
                        model.addAttribute("message", "附件大小必须小于10M!");
                        return model;
                    }
                } else {
                    model.addAttribute("message", "图片仅支持jpg、png、jpeg格式！");
                    return model;
                }
            }
        }
        return null;
    }


    public static String uploadImage(HttpServletRequest request) throws IllegalStateException,
            IOException {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
                .getServletContext());
        // 图片名称
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    return uploadFile(file);
                }
            }
        }
        return null;
    }

    public static void ckeditor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String fileName = uploadImage(request);
        // 结合ckeditor功能
        // imageContextPath为图片在服务器地址，如upload/123.jpg,非绝对路径
        response.setContentType("text/html;charset=UTF-8");
        String callback = request.getParameter("CKEditorFuncNum");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + fileName + "',''" + ")");
        out.println("</script>");
        out.flush();
        out.close();
    }

}
