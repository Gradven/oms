package com.channelsharing.hongqu.oms.common.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 以定制化的bean方式获取到配置文件属性值
 * @author liuhangjun
 *
 */
public class PropertyConfig extends PropertyPlaceholderConfigurer {
  
    private static Map<String, Object> ctxPropertiesMap;
  
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
            Properties props)throws BeansException {
  
        super.processProperties(beanFactory, props);
        //load properties to ctxPropertiesMap
        ctxPropertiesMap = new HashMap<String, Object>();
        
        
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }
  
    //static method for accessing context properties
    private static Object getContextProperty(String key) {
        
        return ctxPropertiesMap.get(key);
    }
    
    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static String getValue(String key){
    	return (String)PropertyConfig.getContextProperty(key);
    }
    
    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static Integer getIntValue(String key){
    	return (Integer)PropertyConfig.getContextProperty(key);
    }
    
    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static Long getLongValue(String key){
    	
    	return Long.parseLong((String) PropertyConfig.getContextProperty(key));
    }
}
