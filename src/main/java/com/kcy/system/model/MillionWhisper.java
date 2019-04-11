package com.kcy.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "MillionWhisper", description = "博客轻语表")
public class MillionWhisper implements Serializable {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("用户id")
    private Integer userid;
    @ApiModelProperty("用户名称")
    private String username;
    @ApiModelProperty("用户头像")
    private String headimg;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("创建时间")
    private Date createtime;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("ip")
    private String ip;
}