package com.kcy.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "VoIndexMillionWhisper", description = "首页轻语数据")
public class VoIndexMillionWhisper implements Serializable {
    @ApiModelProperty("id")
    private long id;
    @ApiModelProperty("内容")
    private String content;
}
