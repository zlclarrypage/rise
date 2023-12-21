package com.rise.authorize.model.user.entity;


import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rise.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author 张牧之
 * @since 2022-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUser对象", description="用户登录表")
public class SysUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String passWord;

    @ApiModelProperty(value = "用户编号")
    private Integer userNo;

    @ApiModelProperty(value = "密钥")
    private String privateKey;

    @ApiModelProperty(value = "总在线时长")
    private Long onlineTime;

    @ApiModelProperty(value = "登录状态")
    private Integer loginStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "用户状态")
    private Integer userStatus;
}
