/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.sensitive.service;

import java.util.List;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.modules.sensitive.entity.SensitiveWord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.modules.sensitive.dao.SensitiveWordDao;

/**
 * 敏感词管理Service
 * @author Karl
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class SensitiveWordService extends CrudService<SensitiveWordDao, SensitiveWord> {

	public SensitiveWord get(Long id) {
		return super.get(id);
	}

	public List<SensitiveWord> findList(SensitiveWord sensitiveWord) {
		return super.findList(sensitiveWord);
	}

	public Page<SensitiveWord> findPage(Page<SensitiveWord> page, SensitiveWord sensitiveWord) {
		return super.findPage(page, sensitiveWord);
	}

	@Transactional(readOnly = false)
	public void save(SensitiveWord sensitiveWord) {
		super.save(sensitiveWord);
	}

	@Transactional(readOnly = false)
	public void delete(SensitiveWord sensitiveWord) {
		super.delete(sensitiveWord);
	}

}
