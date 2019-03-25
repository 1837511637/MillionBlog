package com.kcy.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "MillionBlog", description = "博客标签联系表")
public class MillionAssociation implements Serializable {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "博客id")
    private Integer blogid;
    @ApiModelProperty(value = "标签id")
    private Integer labelid;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogid() {
        return blogid;
    }

    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }

    public Integer getLabelid() {
        return labelid;
    }

    public void setLabelid(Integer labelid) {
        this.labelid = labelid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}