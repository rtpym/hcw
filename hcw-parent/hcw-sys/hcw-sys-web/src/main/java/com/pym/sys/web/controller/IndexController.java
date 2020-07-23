package com.pym.sys.web.controller;

import com.pym.utils.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/15 11:01
 */
@Controller
public class IndexController {
    @GetMapping("/index")
    public String index(HttpServletRequest request) {

        request.setAttribute("token",TokenUtils.getFromCookie(request));
        return "index";
    }
}
