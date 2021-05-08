/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.supplier.service;

import java.util.List;

import com.adobe.xmp.impl.Base64;
import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.modules.supplier.entity.SupplierUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.modules.supplier.dao.SupplierUserDao;

/**
 * 供应商运营用户Service
 *
 * @author Karl
 * @version 2018-06-05
 */
@Service
@Transactional(readOnly = true)
public class SupplierUserService extends CrudService<SupplierUserDao, SupplierUser> {

    public SupplierUser get(Long id) {
        return super.get(id);
    }

    public List<SupplierUser> findList(SupplierUser supplierUser) {
        return super.findList(supplierUser);
    }

    public Page<SupplierUser> findPage(Page<SupplierUser> page, SupplierUser supplierUser) {
        return super.findPage(page, supplierUser);
    }

    @Transactional(readOnly = false)
    public void save(SupplierUser supplierUser) {
        Long id = supplierUser.getId();
        if (id == null) {
            supplierUser.setPassword(DigestUtils.sha512Hex(Base64.encode(supplierUser.getPassword())));
        } else {
            SupplierUser olderUser = get(id);
            if (olderUser != null) {
                String oldPWD = olderUser.getPassword();
                if (oldPWD != null && !oldPWD.equals(supplierUser.getPassword())) {
                    supplierUser.setPassword(DigestUtils.sha512Hex(Base64.encode(supplierUser.getPassword())));
                }

            }
        }
        super.save(supplierUser);

    }

    @Transactional(readOnly = false)
    public void delete(SupplierUser supplierUser) {
        super.delete(supplierUser);
    }

}
