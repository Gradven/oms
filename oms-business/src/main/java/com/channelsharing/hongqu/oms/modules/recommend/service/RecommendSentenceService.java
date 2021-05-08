/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.recommend.service;

import java.util.List;

import com.channelsharing.hongqu.oms.constant.Constant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.modules.recommend.entity.RecommendSentence;
import com.channelsharing.hongqu.oms.modules.recommend.dao.RecommendSentenceDao;

/**
 * 商品推荐语Service
 * @author liuhangjun
 * @version 2018-07-23
 */
@Service
@Transactional(readOnly = true)
public class RecommendSentenceService extends CrudService<RecommendSentenceDao, RecommendSentence> {
	
	public static final String PORTAL_CACHE_PREFIX = Constant.PORTAL_CACHE_PREFIX;  // 清除门户的缓存使用
	
	
	public RecommendSentence get(Long id) {
		return super.get(id);
	}

	public List<RecommendSentence> findList(RecommendSentence recommendSentence) {
		return super.findList(recommendSentence);
	}

	public Page<RecommendSentence> findPage(Page<RecommendSentence> page, RecommendSentence recommendSentence) {
		return super.findPage(page, recommendSentence);
	}
	
	@CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX + "recommendSentenceList", key = "#root.target.PORTAL_CACHE_PREFIX + 'ecommendSentenceList:list'")
	@Transactional(readOnly = false)
	public void save(RecommendSentence recommendSentence) {
		super.save(recommendSentence);
	}
	
	@CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX + "recommendSentenceList", key = "#root.target.PORTAL_CACHE_PREFIX + 'ecommendSentenceList:list'")
	@Transactional(readOnly = false)
	public void delete(RecommendSentence recommendSentence) {
		super.delete(recommendSentence);
	}

}
