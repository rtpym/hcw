package com.pym.sys.ifs;

import com.pym.base.ResultModel;
import com.pym.sys.pojo.SysUser;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
public interface SysUserInterface {
    /* *
     * @Description
     * 根据id获取用户信息
     * @Param [userId]
     * @return com.pym.base.ResultModel
     * @Author zhangping
     * @Date 2020/7/14 20:39
     **/
    @GetMapping("/get/{userId}")
    ResultModel<SysUser> getUserById(@PathVariable("userId") Long userId);
    /* *
     * @Description
     * 账号密码登录
     * @Param []
     * @return com.pym.base.ResultModel
     * @Author zhangping
     * @Date 2020/7/14 20:36
     **/
    @PostMapping("/login")
    ResultModel<String> login(@RequestParam("userName") String userName, @RequestParam("password") String password);

}
