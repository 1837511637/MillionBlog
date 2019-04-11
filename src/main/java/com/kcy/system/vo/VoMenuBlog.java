package com.kcy.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "VoMenuBlog", description = "页面菜单栏最新博客")
public class VoMenuBlog implements Serializable {

    @ApiModelProperty(value = "博客id")
    private long id;
    @ApiModelProperty(value = "博客标题")
    private String title;
}
