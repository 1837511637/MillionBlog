package com.kcy.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "MillionBlog", description = "博客表")
public class MillionBlog implements Serializable {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private Integer userid;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "首图")
    private String image;
    @ApiModelProperty(value = "创建时间")
    private Date createtime;
    @ApiModelProperty(value = "评论数量")
    private Integer evalnum;
    @ApiModelProperty(value = "查看数量")
    private Integer viewnum;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "裁剪内容")
    private String cropcontent;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "是否评价")
    private String iseval;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getEvalnum() {
        return evalnum;
    }

    public void setEvalnum(Integer evalnum) {
        this.evalnum = evalnum;
    }

    public Integer getViewnum() {
        return viewnum;
    }

    public void setViewnum(Integer viewnum) {
        this.viewnum = viewnum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCropcontent() {
        return cropcontent;
    }

    public void setCropcontent(String cropcontent) {
        this.cropcontent = cropcontent == null ? null : cropcontent.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIseval() {
        return iseval;
    }

    public void setIseval(String iseval) {
        this.iseval = iseval == null ? null : iseval.trim();
    }
}