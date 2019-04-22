package com.kcy.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "VoWhisperEvaluate", description = "轻语页评论数据集")
public class VoWhisperEvaluate {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("评论用户头像")
    private String headimg;
    @ApiModelProperty("评论用户名称")
    private String username;
    @ApiModelProperty("网站")
    private String webLink;
    @ApiModelProperty("评论信息")
    private String content;
    @ApiModelProperty("是否是作者")
    private String isuser;
}
