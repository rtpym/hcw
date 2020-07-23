package com.pym.pay.web.controller;

import com.pym.base.BaseException;
import com.pym.base.ResultModel;
import com.pym.constants.ResultCode;
import com.pym.pay.feign.PayServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/18 19:36
 */
@Controller
public class AlipayController {
    @Autowired
    private PayServiceFeign payServiceFeign;
    @GetMapping("/alipay/page/{payToken}")
    public void alipay(@PathVariable("payToken") String payToken, HttpServletResponse response) throws IOException {
        ResultModel resultModel = payServiceFeign.getAlipayPageByToke(payToken);
        if (resultModel.getCode() != ResultCode.CODE_SUCCESS) {
            throw new BaseException(resultModel.getMsg());
        }
        response.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(resultModel.getData());
        printWriter.close();
    }
    @GetMapping("/alipay/return")
    @ResponseBody
    public ResultModel alipayReturn(@RequestParam Map<String,String> params) {
        System.out.println(params);
        return payServiceFeign.alipayReturn(params);
    }
    @PostMapping("/alipay/notify")
    @ResponseBody
    public String alipayNotify( @RequestParam Map<String,String> params) {
        System.out.println(params);
        ResultModel resultModel = payServiceFeign.alipayNotify(params);
        if (resultModel.getCode() == 200) {
            return "success";
        }
        return "fail";
    }
}
