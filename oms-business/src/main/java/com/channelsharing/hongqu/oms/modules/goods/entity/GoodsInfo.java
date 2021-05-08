/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.channelsharing.hongqu.oms.common.utils.StringUtils;
import com.channelsharing.hongqu.oms.modules.product.entity.ProductInfo;
import com.channelsharing.hongqu.oms.common.persistence.DataEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.slf4j.LoggerFactory;

/**
 * 商品信息Entity
 *
 * @author Karl
 * @version 2018-06-07
 */
@Data
public class GoodsInfo extends DataEntity<GoodsInfo> {

    private static final long serialVersionUID = 1L;
    @Length(min = 0, max = 128, message = "商品SN长度必须介于 0 和 128 之间")
    private String sn;        // 商品SN

    private Long categoryId;        // 分类编号

    private String categoryName;

    @Length(min = 0, max = 4000, message = "图片长度必须介于 0 和 4000 之间")
    private String picUrls;        // 图片

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<String> picList;

    public List<String> getPicList() {
        if (StringUtils.isNotEmpty(this.picUrls)) {
            Object picUrlsJson = JSON.parse(this.picUrls);
            if (picUrlsJson instanceof JSONArray) {
                return ((JSONArray) picUrlsJson).toJavaList(String.class);
            }
        }
        return null;
    }

    private Long supplierId;        // 供应商编号
    private String supplierName;        // 供应商名称

    @Length(min = 1, max = 128, message = "商品名称长度必须介于 1 和 128 之间")
    private String name;        // 商品名称

    @Length(min = 0, max = 255, message = "商品关键字长度必须介于 0 和 255 之间")
    private String keywords;        // 商品关键字

    private Integer salesVolume;        // 销售量

    @NotNull(message = "零售价不能为空")
    private BigDecimal retailPrice;        // 零售价

    @Range(min = 0, max = 1000000, message = "库存范围必须介于 0 和 1000000 之间")
    private Integer storeNumber;        // 库存

    @NotNull(message = "单价不能为空")
    private BigDecimal unitPrice;        // 单价

    @Length(min = 0, max = 24, message = "商品单位长度必须介于 0 和 24 之间")
    private String unit;        // 商品单位

    @NotNull(message = "利润不能为空")
    private BigDecimal profit;        // 利润

    private Integer onSaleFlag;        // 是否上架

    private Integer approveStatus;

    private String shortDescription;        // 商品介绍

    private GoodsDescription description;

    private Integer createSuId;        // 供应商创建者编号

    private Integer updateSuId;        // 供应商修改者编号


    public GoodsInfo() {
        super();
    }

    public GoodsInfo(Long id) {
        super(id);
    }

    private List<ProductInfo> relativeProducts;

    private ProductInfo goodsStatistics;

    private String cover;

}
