package com.channelsharing.hongqu.oms.common.utils.storage;

import com.alibaba.druid.util.StringUtils;

/**
 * Created by liuhangjun on 2017/11/29.
 */
public class CloudStorageFactory {
    
    private CloudStorageFactory(){}
    
    private static ICloudStorage cloudStorage;
    
    private static final String ALIYUN_OSS = "ALIYUN_OSS";
    private static final String QINIU_STORAGE = "QINIU_STORAGE";
    
    // 设定由那个服务商提供服务
    private static final String STORAGE_SERVER = ALIYUN_OSS;
    
    /**
     * 上传图片
     * @return
     */
    public static synchronized ICloudStorage getCloudStorage(){
    
        if (cloudStorage == null) {
    
            if (StringUtils.equals(STORAGE_SERVER, ALIYUN_OSS)) {
                // 配置是用阿里云
                cloudStorage = new AliyunOss();
            }else if (StringUtils.equals(STORAGE_SERVER, QINIU_STORAGE)){
                // 配置为七牛云
                ICloudStorage cloudStorage = new QiniuCloudStorage();
            }else{
                // 默认配置是用阿里云
                cloudStorage = new AliyunOss();
            }
            
        }
        
        return cloudStorage;
    }
}
