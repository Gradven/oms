/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.user.service;

import java.util.List;

import com.adobe.xmp.impl.Base64;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.constant.Constant;
import com.channelsharing.hongqu.oms.modules.user.entity.UserInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.modules.user.dao.UserInfoDao;


/**
 * 用户信息Service
 * @author Karl
 * @version 2018-06-13
 */
@Service
@Transactional(readOnly = true)
public class UserInfoService extends CrudService<UserInfoDao, UserInfo> {
	public static final String PORTAL_CACHE_PREFIX = Constant.PORTAL_CACHE_PREFIX;
	public UserInfo get(Long id) {
		return super.get(id);
	}

	public List<UserInfo> findList(UserInfo userInfo) {
		return super.findList(userInfo);
	}

	public Page<UserInfo> findPage(Page<UserInfo> page, UserInfo userInfo) {
		return super.findPage(page, userInfo);
	}

	@Transactional(readOnly = false)
	@CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX + "userInfo", key = "#root.target.PORTAL_CACHE_PREFIX + 'userInfo:id:' + #userInfo.id", condition = "#userInfo.id!=null")

	public void save(UserInfo userInfo) {
		Long id = userInfo.getId();
		if (id == null) {
			userInfo.setPassword(DigestUtils.sha512Hex(Base64.encode(userInfo.getPassword())));
		} else {
			UserInfo olderUser = get(id);
			if (olderUser != null) {
				String oldPWD = olderUser.getPassword();
				if (oldPWD != null && !oldPWD.equals(userInfo.getPassword())) {
					userInfo.setPassword(DigestUtils.sha512Hex(Base64.encode(userInfo.getPassword())));
				}

			}
		}

		super.save(userInfo);
	}

	@Transactional(readOnly = false)
	@CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX + "userInfo", key = "#root.target.PORTAL_CACHE_PREFIX + 'userInfo:id:' + #userInfo.id", condition = "#userInfo.id!=null")
	public void delete(UserInfo userInfo) {
		super.delete(userInfo);
	}

}
