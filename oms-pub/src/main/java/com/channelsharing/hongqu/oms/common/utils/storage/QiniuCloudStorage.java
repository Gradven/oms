package com.channelsharing.hongqu.oms.common.utils.storage;

import com.channelsharing.hongqu.oms.common.config.PropertyConfig;
import com.channelsharing.hongqu.oms.common.utils.StringUtils;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liuhangjun on 2017/11/29.
 */
public class QiniuCloudStorage implements ICloudStorage {
    
    private static Logger logger = LoggerFactory.getLogger(QiniuCloudStorage.class);
    
    private static String accessKey = PropertyConfig.getValue("qiniu.accessKey");
    private static String secretKey = PropertyConfig.getValue("qiniu.secretKey");
    
    // 华东:zone0(),华东:zone1(), 华南：zone2()，北美：zoneNa0()
    private static Zone zone = Zone.huanan();   // 华南机房
    private static  Configuration cfg = new Configuration(zone);
    
    private static String defaultBucket = PropertyConfig.getValue("qiniu.bucket.name.default");
    
    
    public static Auth getAuth() {
        
        return Auth.create(accessKey, secretKey);
    }
    
    
    @Override
    public String uploadFile(String bucketName, InputStream stream, String key) {
    
        return uploadFile(stream, key, bucketName, null);
    }
    
    @Override
    public void deleteFile(String bucketName, String key) {
    
        BucketManager bucketManager = new BucketManager(getAuth(), cfg);
        try {
            bucketManager.delete(bucketName, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            logger.error("delete qiniu storage file error, error code is {}, detail is {}", ex.code(), ex.response.toString());
        }
    
    }
    
    @Override
    public String getPrivateUri(String bucketName, String key,  Long expiredTime) {
        
        if (StringUtils.isBlank(bucketName)){
            bucketName = defaultBucket;
        }
    
        BucketManager bucketManager = new BucketManager(getAuth(), cfg);
        
        String prefixUri = "";
    
        try {
            String[] array = bucketManager.domainList(bucketName);
            if (prefixUri.length() > 0){
                prefixUri = array[0];
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        
        String url = prefixUri + key;
    
        if (expiredTime == null){
            expiredTime = EXPIRED_TIME;
        }
        
        String privateUrl = getAuth().privateDownloadUrl(url, expiredTime);
    
        logger.debug("qiniu private url is {}", privateUrl);
    
        return privateUrl;
    }
    
    
    
    /**
     * 上传文件
     *
     * @param stream
     * @param key
     * @param bucketName
     * @param fops
     * @throws IOException
     * @fops 转码参数
     */
    private static String uploadFile(InputStream stream, String key, String bucketName, String fops) {
    
        if (StringUtils.isBlank(bucketName)){
            bucketName = defaultBucket;
        }
        
        // 构造一个带指定Zone对象的配置类.
        Configuration cfg = new Configuration(zone);
        UploadManager uploadManager = new UploadManager(cfg);
        
        Auth auth = getAuth();
        
        StringMap stringMap = new StringMap();
        
        if (StringUtils.isNotBlank(fops)) {
            String pfops = fops + "|saveas/" + UrlSafeBase64.encodeToString(bucketName + ":" + key); // 指定转码后的文件bucket和名字
            stringMap.putNotEmpty("persistentOps", pfops);
            //stringMap.putNotEmpty("persistentPipeline", "xdvideo_queue");
            //stringMap.putNotEmpty("persistentPipeline", pipeline);//以后可以在七牛上新增队列，以提高处理速度
        }
        
        String upToken = auth.uploadToken(bucketName, key, 3600, stringMap, true);
        
        logger.debug("Upload file upToken id is [{}]  ", upToken);
    
        Response response = null;
        try {
            response = uploadManager.put(stream, key, upToken, null, null);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    
        logger.debug("Upload file key is [{}], duration is [{}]  ", key, response.duration);
        
        // 解析上传成功的结果
        DefaultPutRet putRet = null;
        try {
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    
        logger.debug("Qiniu Cloud Storage putRet key: " + putRet.key);
        logger.debug("Qiniu Cloud Storage putRet hash: " + putRet.hash);
    
        return putRet.key;
    }
}
