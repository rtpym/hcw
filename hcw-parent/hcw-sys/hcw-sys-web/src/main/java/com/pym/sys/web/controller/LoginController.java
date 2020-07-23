package com.pym.sys.web.controller;

import com.pym.base.ResultModel;
import com.pym.constants.ResultCode;
import com.pym.sys.feign.SysUserFeign;
import com.pym.utils.ObjUtils;
import com.pym.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/15 2:05
 */
@Controller
public class LoginController {
    @Autowired
    private SysUserFeign sysUserFeign;
    @GetMapping("/get/{userId}")
    @ResponseBody
    public ResultModel get(@PathVariable("userId") Long userId) {
       return sysUserFeign.getUserById(userId);
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam("userName") String userName, @RequestParam("password") String password) {
        ResultModel<String> resultModel = sysUserFeign.login(userName,password);
        //登录失败
        if (resultModel.getCode() != ResultCode.CODE_SUCCESS) {
            request.setAttribute("userName", userName);
            request.setAttribute("password", password);
            request.setAttribute("errorMsg", resultModel.getMsg());
            return "/login";
        }
        //登录成功
        //将token设置进cookie
        TokenUtils.setToCooke(resultModel.getData(),response);
        return "redirect:/index";
    }
    @PostMapping("/qqLogin")
    public String qqLogin(@RequestParam String openId) {
        return null;
    }
}
