package com.kcy.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "VoArchivesBlog", description = "归档博客数据")
public class VoArchivesBlog implements Serializable {
    @ApiModelProperty("博客id")
    private Integer id;
    @ApiModelProperty("博客title")
    private String title;
    @ApiModelProperty("博客时间")
    private String createtime;
}
