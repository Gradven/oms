package com.channelsharing.hongqu.oms.common.utils.storage;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLConnection;

/**
 * Created by liuhangjun on 2018/1/25.
 */
@Controller
@RequestMapping(value = "${adminPath}/common/storage")
public class StorageController {
    
    @RequestMapping(value = "viewFile")
    public String viewFile(@RequestParam String key, Model model) {
        String mimeType = URLConnection.getFileNameMap().getContentTypeFor(key);
        String extension = FilenameUtils.getExtension(key);
        String encryptedUrl = CloudStorageFactory.getCloudStorage().getPrivateUri("", key, null);
        model.addAttribute("encryptedUrl", encryptedUrl);
        
        if (StringUtils.startsWith(mimeType, "image")) {
            model.addAttribute("fileType", "image");
            return "common/viewFile";
        } else if (StringUtils.equalsIgnoreCase(extension, "mp4")) {
            model.addAttribute("fileType", "video");
            return "common/viewFile";
        } else if (StringUtils.equalsAnyIgnoreCase(extension, "pdf")) {
            model.addAttribute("fileType", "pdf");
            return "common/viewFile";
        } else {
            return "redirect:" + encryptedUrl;
        }
    }
}
