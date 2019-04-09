package com.kcy.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MillionBlog", description = "博客表")
public class MillionBlog implements Serializable {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private Integer userid;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "首图")
    private String image;
    @ApiModelProperty(value = "创建时间")
    private Date createtime;
    @ApiModelProperty(value = "评论数量")
    private Integer evalnum;
    @ApiModelProperty(value = "查看数量")
    private Integer viewnum;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "裁剪内容")
    private String cropcontent;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "是否评价")
    private String iseval;
}