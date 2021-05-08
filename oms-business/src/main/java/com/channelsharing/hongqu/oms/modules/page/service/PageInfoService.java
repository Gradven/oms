/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.page.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.common.service.TreeService;
import com.channelsharing.hongqu.oms.modules.page.entity.PageInfo;
import com.channelsharing.hongqu.oms.modules.page.dao.PageInfoDao;

/**
 * 页面信息Service
 * @author liuhangjun
 * @version 2018-07-26
 */
@Service
@Transactional(readOnly = true)
public class PageInfoService extends TreeService<PageInfoDao, PageInfo> {

	public PageInfo get(Long id) {
		return super.get(id);
	}

	public List<PageInfo> findList(PageInfo pageInfo) {
		return super.findList(pageInfo);
	}

	@Transactional(readOnly = false)
	public void save(PageInfo pageInfo) {
		super.save(pageInfo);
	}

	@Transactional(readOnly = false)
	public void delete(PageInfo pageInfo) {
		super.delete(pageInfo);
	}

}
