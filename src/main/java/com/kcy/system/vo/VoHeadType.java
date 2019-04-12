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
@ApiModel(value = "VoHeadType", description = "头部类型")
public class VoHeadType implements Serializable {
    @ApiModelProperty("类型id")
    private Integer id;
    @ApiModelProperty("类型名称")
    private String name;
}
