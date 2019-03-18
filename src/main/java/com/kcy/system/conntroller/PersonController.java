package com.kcy.system.conntroller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kcy.common.base.BaseController;
import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.system.dao.PersonMapper;
import com.kcy.system.model.FSPerson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@Api(value = "用户接口")
@RestController
@RequestMapping("/person")
public class PersonController extends BaseController {

    @Autowired
    private PersonMapper personMapper;

    @ApiOperation(value = "用户分页列表", notes = "可根据参数查询")
    @GetMapping("/findPersonPage")
    public ResponseWrapper findUserPage(FSPerson fsPerson) throws Exception {
        //每页十条
        Page <Object> objects = PageHelper.startPage(1, 10);
        List <FSPerson> all = personMapper.findAll();
        //获取总页数
        int pages = objects.getPages();
        ResponseWrapper responseWrapper = ResponseUtils.successResponse("datas", all, "查询成功");
        responseWrapper.setTotalpage(pages);
        return responseWrapper;
    }

    @ApiOperation(value = "获取用户", notes = "根据id查询用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required=true, dataType = "Long")
    @GetMapping("/queryPersonById")
    public ResponseWrapper queryUserById(long id) {

        return null;
    }

}
