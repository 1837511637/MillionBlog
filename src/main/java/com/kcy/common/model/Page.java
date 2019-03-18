package com.kcy.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(value = "Page", description = "分页")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Page implements Serializable {
    @ApiModelProperty(value = "第几页")
    private int page = 1;
    @ApiModelProperty(value = "条数")
    private int rows = 20;
}
