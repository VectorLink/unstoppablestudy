package com.unstoppable.unstoppablestudy.dao.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 站点信息表
 * </p>
 *
 * @author ${author}
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("populate_site")
@Builder
public class PopulateSite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 站点信息主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编码
     */
    private String cataLogCode;

    /**
     * 域名
     */
    private String domain;

    /**
     * 网站名称
     */
    private String siteName;

    /**
     * 网站名称1
     */
    private String altSiteName;

    /**
     * 网站logo
     */
    private String siteLogo;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 卖家名称
     */
    private String merchantName;

    /**
     * 排序权重
     */
    private Integer sortBy;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 创建日期
     */
    private LocalDateTime createDate;

    /**
     * 更新日期
     */
    private LocalDateTime updateDate;

    /**
     * PopulationSite类别
     */
    private Integer populateSiteCatId;

    /**
     * 是否标记
     */
    private Integer isMarkup;

    /**
     * 费率
     */
    private BigDecimal handlingFee;


}
