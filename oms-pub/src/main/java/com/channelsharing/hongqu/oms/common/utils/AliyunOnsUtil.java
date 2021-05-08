/**
 *
 */
package com.channelsharing.hongqu.oms.common.utils;

import java.util.Properties;

import com.channelsharing.hongqu.oms.common.config.PropertyConfig;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liuhangjun
 * @date 2017年3月16日
 */
public class AliyunOnsUtil {
	private static Logger logger = LoggerFactory.getLogger(AliyunOnsUtil.class);

	private static final String ACCESSKEY_ID = PropertyConfig.getValue("aliyun.accessKeyId");
	private static final String ACCESSKEY_SECRET = PropertyConfig.getValue("aliyun.accessKeySecret");

	private static final String PID_GLOBALE_ORDER = PropertyConfig.getValue("aliyun.ons.pid.global.order");
	private static final String TOPIC_GLOBE_ORDER = PropertyConfig.getValue("aliyun.ons.topic.global.order");


	private static boolean globleOrderStartFlag = false;
	private static Producer producerGlobeOrder = null;

	/**
	 * 文章内容新建发送消息
	 *
	 * @param msgBody
	 */
	public static void sendGlobeOrderChapterCreate(String tag, String msgBody) {
		AliyunOnsUtil.sendGlobelOrder(tag, msgBody);
	}

	/**
	 * 文章内容修改发送消息
	 *
	 * @param msgBody
	 */
	public static void sendGlobeOrderChapterModify(String tag, String msgBody) {
		AliyunOnsUtil.sendGlobelOrder(tag, msgBody);
	}

	private static void sendGlobelOrder(String tag, String msgBody) {

		// 控制生产者的只start一次
		if (globleOrderStartFlag == false) {
			Properties properties = new Properties();
			properties.put(PropertyKeyConst.AccessKey, ACCESSKEY_ID);// 鉴权用AccessKey，在阿里云服务器管理控制台创建
			properties.put(PropertyKeyConst.SecretKey, ACCESSKEY_SECRET);// 鉴权用SecretKey，在阿里云服务器管理控制台创建
			properties.put(PropertyKeyConst.ProducerId, PID_GLOBALE_ORDER);

			producerGlobeOrder = ONSFactory.createProducer(properties);
			// 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可
			producerGlobeOrder.start();

			globleOrderStartFlag = true;
		}

		Message msg = new Message( //
				// 在控制台创建的Topic，即该消息所属的Topic名称
				TOPIC_GLOBE_ORDER,
				// Message Tag,
				// 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在MQ服务器过滤
				tag,
				// Message Body
				// 任何二进制形式的数据， MQ不做任何干预，
				// 需要Producer与Consumer协商好一致的序列化和反序列化方式
				msgBody.getBytes());
		// 设置代表消息的业务关键属性，请尽可能全局唯一，以方便您在无法正常收到消息情况下，可通过MQ控制台查询消息并补发
		// 注意：不设置也不会影响消息正常收发
		msg.setKey(IdGen.uuid());
		// 发送消息，只要不抛异常就是成功
		// 打印Message ID，以便用于消息发送状态查询
		SendResult sendResult = producerGlobeOrder.send(msg);
		logger.info("Send Message success. Message ID is: " + sendResult.getMessageId());

	}

}
