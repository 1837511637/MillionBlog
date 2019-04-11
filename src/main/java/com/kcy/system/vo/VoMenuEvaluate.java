package com.kcy.system.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "VoMenuEvaluate", description="页面菜单栏最新留言")
public class VoMenuEvaluate implements Serializable {
    @ApiModelProperty(value = "称呼")
    private String name;
    @ApiModelProperty(value = "网站链接")
    private String weblink;
    @ApiModelProperty(value = "留言内容")
    private String content;
}
