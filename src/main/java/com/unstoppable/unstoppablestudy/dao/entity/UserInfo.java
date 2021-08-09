package com.unstoppable.unstoppablestudy.dao.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_info")
@ApiModel( description = "人员信息")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(notes = "主键")
    private Integer id;

    /**
     * 名字
     */
    @ApiModelProperty(notes ="名称")
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty(notes ="年纪")
    private Integer age;

    /**
     * 薪水
     */
    @ApiModelProperty(notes ="薪水")
    private BigDecimal salary;


}
