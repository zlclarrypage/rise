package com.rise.gateway.model.route;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.rise.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>
 * 网关路由表
 * </p>
 *
 * @author 张牧之
 * @since 2022-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRoute对象", description="网关路由表")
public class SysRoute extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "uri地址")
    private String uri;

    @ApiModelProperty("uri类型 1服务名，2链接")
    private Integer uriType;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "名称")
    private String routeName;


    @ApiModelProperty("路由下的断言")
    @TableField(exist = false)
    private List<SysPredicates> sysPredicatesList;

    @ApiModelProperty("路由下的过滤器")
    @TableField(exist = false)
    private List<SysFilters> sysFiltersList;


    public List<SysPredicates> getSysPredicatesList() {
        return null == sysPredicatesList ? new ArrayList<>() : sysPredicatesList;
    }

    public List<SysFilters> getSysFiltersList() {
        return null == sysFiltersList ? new ArrayList<>() : sysFiltersList;
    }
}
