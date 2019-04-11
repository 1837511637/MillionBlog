package com.kcy.system.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Log
@Api(value = "页面博客数据")
public class VoBlog implements Serializable {
    @ApiModelProperty(value = "博客id")
    private Integer id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "首图")
    private String img;
    @ApiModelProperty(value = "创建时间")
    private String createtime;
    @ApiModelProperty(value = "博客类型")
    private String type;
    @ApiModelProperty(value = "评论数量")
    private String evalnum;
    @ApiModelProperty(value = "阅读数量")
    private String viewnum;
    @ApiModelProperty(value = "裁剪内容")
    private String content;
}
