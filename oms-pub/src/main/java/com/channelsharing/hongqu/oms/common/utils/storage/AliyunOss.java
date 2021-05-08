package com.channelsharing.hongqu.oms.common.utils.storage;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.aliyun.oss.OSSClient;
import com.channelsharing.hongqu.oms.common.config.PropertyConfig;
import com.channelsharing.hongqu.oms.common.utils.SpringContextHolder;
import com.channelsharing.hongqu.oms.common.utils.StringUtils;

/**
 * Created by liuhangjun on 2017/11/29.
 */
public class AliyunOss implements ICloudStorage {
	// endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
	// 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
	// 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
	// endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
	// 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
	private static String accessKeyId = PropertyConfig.getValue("aliyun.accessKeyId");
	private static String accessKeySecret = PropertyConfig.getValue("aliyun.accessKeySecret");
	private static String endpoint = PropertyConfig.getValue("aliyun.oss.endpoint");
	private static String host = PropertyConfig.getValue("aliyun.oss.host");

	// 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
	// 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
	private static OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

	private static String defaultBucket = PropertyConfig.getValue("aliyun.oss.bucket.name.default");

	@Override
	public String uploadFile(String bucketName, InputStream stream, String key) {

		if (StringUtils.isBlank(bucketName)) {
			bucketName = defaultBucket;
		}

		ossClient.putObject(bucketName, key, stream);
		
		String uri;
		if (StringUtils.isEmpty(host)){
			throw new RuntimeException("Please config aliyun host of oss");
		}else {
			if (StringUtils.endsWith(host, "/")){
				uri = host + key;
			}else{
				uri = host + "/" + key;
			}
			
		}
		

		return uri;
	}

	@Override
	public void deleteFile(String bucketName, String key) {

		if (StringUtils.isBlank(bucketName)) {
			bucketName = defaultBucket;
		}

		ossClient.deleteObject(bucketName, key);

	}

	@Override
	public String getPrivateUri(String bucketName, String key, Long expiredTime) {

		if (StringUtils.isBlank(bucketName)) {
			bucketName = defaultBucket;
		}

		if (expiredTime == null) {
			expiredTime = EXPIRED_TIME;
		}

		Date expiration = Date
				.from(LocalDateTime.now().plusSeconds(expiredTime).atZone(ZoneId.systemDefault()).toInstant());

		OSSClient ossClient = SpringContextHolder.getBean(OSSClient.class);
		URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);

		return url.toString();
	}
}
