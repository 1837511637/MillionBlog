package com.kcy.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MillionUser", description = "用户表")
public class MillionUser implements Serializable {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "创建时间")
    private Date createtime;
    @ApiModelProperty(value = "网站链接")
    private String weblink;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "ip")
    private String ip;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "密码")
    private String password;
}