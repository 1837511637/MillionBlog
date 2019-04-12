package com.kcy.system.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "VoEvaluate", description = "盖楼设计评论")
public class VoEvaluate implements Serializable {
    @ApiModelProperty("评论id")
    private Integer evalid;
    @ApiModelProperty("头像")
    private String heaimg;
    @ApiModelProperty("名称")
    private String username;
    @ApiModelProperty("网站链接")
    private String webLink;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("评价时间")
    private String createtime;
    @ApiModelProperty("是否有回复")
    private String ischild;
    @ApiModelProperty("该评论下所有子回复")
    private List<VoEvaluate> voEvaluateList;
}
