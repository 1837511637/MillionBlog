package com.kcy.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MillionEvaluation", description = "评论表")
public class MillionEvaluation implements Serializable {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "博客id")
    private Integer blogid;
    @ApiModelProperty(value = "头像")
    private String headimg;
    @ApiModelProperty(value = "称呼")
    private String name;
    @ApiModelProperty(value = "ip")
    private String ip;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "网站链接")
    private String weblink;
    @ApiModelProperty(value = "创建时间")
    private Date createtime;
    @ApiModelProperty(value = "评论类型")
    private String type;
    @ApiModelProperty(value = "回复id")
    private Integer evaluation;
    @ApiModelProperty(value = "评论内容")
    private String content;
    @ApiModelProperty(value = "第一条评论id")
    private Integer firstevalid;
    @ApiModelProperty(value = "是否是父节点")
    private String ischild;
}