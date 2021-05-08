package com.channelsharing.hongqu.oms.common.utils.storage;

import java.io.InputStream;

/**
 * Created by liuhangjun on 2017/11/29.
 */
public interface ICloudStorage {
    
    Long EXPIRED_TIME = 3600L;
    
    /**
     *
     * @param bucketName  如果此参数为null或者空，那么使用系统默认的bucket
     * @param stream	流
     * @param key	文件key
     * @return 文件的访问uri
     */
    String uploadFile(String bucketName, InputStream stream, String key);
    
    /**
     * @param bucketName  如果此参数为null或者空，那么使用系统默认的bucket
     * 删除文件
     * @param
     */
    void deleteFile(String bucketName, String key);
    
    /**
     * @param bucketName  如果此参数为null或者空，那么使用系统默认的bucket
     * 获取私有访问地址
     * @param key
     * @param expiredTime 如果此参数为null或者空，那么使用系统默认的过期时间
     * @return
     */
    String getPrivateUri(String bucketName, String key, Long expiredTime);
}
