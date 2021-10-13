package com.ymm.basic.project.controller;


import com.ymm.basic.project.enums.ResultEnum;
import com.ymm.basic.project.form.AddUserForm;
import com.ymm.basic.project.form.ListUserForm;
import com.ymm.basic.project.service.IUserService;
import com.ymm.basic.project.utils.ResultVoUtil;
import com.ymm.basic.project.vo.ResultVo;
import com.ymm.basic.project.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author y
 * @date Created in 2020/3/6 4:30 下午
 * Utils: Intellij Idea
 * Description: 用户前端控制器
 */
@RestController
@Api(tags = "用户")
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    /**
     * 添加用户
     * @param userForm 表单数据
     * @return 成功或者失败
     */
    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public ResultVo addUser(@Validated @RequestBody AddUserForm userForm){
        if(userService.addUser(userForm)){
            return ResultVoUtil.success();
        }else{
            return ResultVoUtil.error(ResultEnum.ADD_ERROR);
        }
    }

    /**
     * 获取用户列表
     * @param listUserForm 表单数据
     * @return 用户列表
     */
    @ApiOperation("获取用户列表")
    @GetMapping("/listUser")
    @ApiResponses(
            @ApiResponse(code = 200, message = "操作成功", response = UserVo.class)
    )
    public ResultVo listUser(@Validated ListUserForm listUserForm){
    	//test
        return ResultVoUtil.success(
        		userService.listUser(listUserForm)
		);
    }

    /**
     * 删除用户
     * @param id 用户编号
     * @return 成功或者失败
     */
    @ApiOperation("删除用户")
    @DeleteMapping("/deleteUser/{id}")
    public ResultVo deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
        return ResultVoUtil.success();
    }
}
