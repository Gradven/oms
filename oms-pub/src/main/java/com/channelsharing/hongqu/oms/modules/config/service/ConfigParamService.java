/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.config.service;

import java.util.List;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.constant.Constant;
import com.channelsharing.hongqu.oms.modules.config.entity.ConfigParam;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.modules.config.dao.ConfigParamDao;

/**
 * 系统配置项管理Service
 * @author Karl
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class ConfigParamService extends CrudService<ConfigParamDao, ConfigParam> {

	public static final String PORTAL_CACHE_PREFIX = Constant.PORTAL_CACHE_PREFIX;  // 清除门户的缓存使用

	public ConfigParam get(Long id) {
		return super.get(id);
	}

	public List<ConfigParam> findList(ConfigParam configParam) {
		return super.findList(configParam);
	}

	public Page<ConfigParam> findPage(Page<ConfigParam> page, ConfigParam configParam) {
		return super.findPage(page, configParam);
	}

	@Transactional(readOnly = false)
	@CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX + "configParam", key = "#root.target.PORTAL_CACHE_PREFIX + 'configParam:key:' + #configParam.configKey", condition = "#configParam.configKey!=null")
	public void save(ConfigParam configParam) {
		super.save(configParam);
	}


	@Transactional(readOnly = false)
	@CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX + "configParam", key = "#root.target.PORTAL_CACHE_PREFIX + 'configParam:key:' + #configParam.configKey", condition = "#configParam.configKey!=null")
	public void delete(ConfigParam configParam) {
		super.delete(configParam);
	}

}
