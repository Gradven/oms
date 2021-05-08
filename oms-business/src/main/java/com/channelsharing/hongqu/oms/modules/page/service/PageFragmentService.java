/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.page.service;

import java.util.List;

import com.channelsharing.hongqu.oms.constant.Constant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.modules.page.entity.PageFragment;
import com.channelsharing.hongqu.oms.modules.page.dao.PageFragmentDao;

/**
 * 页面区块配置Service
 * @author liuhangjun
 * @version 2018-07-26
 */
@Service
@Transactional(readOnly = true)
public class PageFragmentService extends CrudService<PageFragmentDao, PageFragment> {
	
	public static final String PORTAL_CACHE_PREFIX = Constant.PORTAL_CACHE_PREFIX;  // 清除门户的缓存使用

	public PageFragment get(Long id) {
		return super.get(id);
	}

	public List<PageFragment> findList(PageFragment pageFragment) {
		return super.findList(pageFragment);
	}

	public Page<PageFragment> findPage(Page<PageFragment> page, PageFragment pageFragment) {
		return super.findPage(page, pageFragment);
	}
	
	@CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX + "pageFragment", key = "#root.target.PORTAL_CACHE_PREFIX + 'pageFragment:keyword:' + #pageFragment.keyword", condition = "#pageFragment.keyword!=null")
	@Transactional(readOnly = false)
	public void save(PageFragment pageFragment) {
		super.save(pageFragment);
	}
	
	@CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX + "pageFragment", key = "#root.target.PORTAL_CACHE_PREFIX + 'pageFragment:keyword:' + #pageFragment.keyword", condition = "#pageFragment.keyword!=null")
	@Transactional(readOnly = false)
	public void delete(PageFragment pageFragment) {
		super.delete(pageFragment);
	}

}
