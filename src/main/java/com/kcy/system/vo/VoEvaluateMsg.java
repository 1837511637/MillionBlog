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
@ApiModel(value="VoEvaluateMsg", description = "用户评论信息模版")
public class VoEvaluateMsg implements Serializable {
    @ApiModelProperty("评论者名称")
    private String name;
    @ApiModelProperty("评论者网站")
    private String weblink;
    @ApiModelProperty("评论者邮箱")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
