package com.kcy.system.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "MillionAd", description = "博客广告表")
public class MillionAd implements Serializable {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("广告位置")
    private String position;
    @ApiModelProperty("广告图片")
    private String image;
    @ApiModelProperty("广告跳转路径")
    private String weblink;
    @ApiModelProperty("创建时间")
    private Date createtime;
    @ApiModelProperty("开始时间")
    private Date starttime;
    @ApiModelProperty("结束时间")
    private Date endtime;
    @ApiModelProperty("状态")
    private String status;

}