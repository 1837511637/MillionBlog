package com.kcy.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(description = "轻语页数据集", value = "VoWhispers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoWhispers {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("轻语头像")
    private String headimg;
    @ApiModelProperty("轻语名称")
    private String username;
    @ApiModelProperty("时间")
    private String createtime;
    @ApiModelProperty("网站")
    private String webLink;
    @ApiModelProperty("轻语")
    private String content;
    @ApiModelProperty("轻语评论")
    private List<VoWhisperEvaluate> voWhisperEvaluateList;
}
