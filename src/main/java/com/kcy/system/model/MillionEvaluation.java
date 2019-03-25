package com.kcy.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

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

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink == null ? null : weblink.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }
}