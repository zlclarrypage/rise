package com.rise.gateway.model.route;


import java.io.Serializable;

import com.rise.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 网关断言表
 * </p>
 *
 * @author 张牧之
 * @since 2022-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysPredicates对象", description="网关断言表")
public class SysPredicates extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "路由id")
    private Long routeId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "断言配置(json)")
    private String args;

    @ApiModelProperty(value = "名称")
    private String predicateName;
}
