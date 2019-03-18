package com.kcy.system.model;

import com.kcy.common.model.Page;
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
@ApiModel(value = "FSPerson", description = "系统用户")
public class FSPerson extends Page implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "生日")
    private Date birthday;
    @ApiModelProperty(value = "真实地址")
    private String address;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "头像")
    private String headimage;
    @ApiModelProperty(value = "状态")
    private String status;
    
}