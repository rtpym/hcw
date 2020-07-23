package com.pym.base;


import com.pym.constants.ResultMsgEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 *  全局异常处理基类
 * @author zhangping
 * @version 1.0
 * @date 2020/7/14 15:37
 */

@Slf4j
public class BaseExceptionHandler {
    /* *
     * @Description
     * 处理自定义异常
     * @Param [be]
     * @return com.pym.base.ResultModel
     * @Author zhangping
     * @Date 2020/7/14 15:43
     **/
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ResultModel baseException(BaseException be, HttpServletRequest request, HttpServletResponse response) {
        log.error("自定义异常：{}", be.getMsg());
        return ResultModel.msgModel(be.getCode(),be.getMsg());
    }

    /* *
     * @Description
     * 处理非自定义异常
     * @Param [e, request, response]
     * @return com.pym.base.ResultModel
     * @Author zhangping
     * @Date 2020/7/14 15:51
     **/
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultModel exception(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("系统异常：{}", e);
        return ResultModel.msgModel(ResultMsgEnum.SERVER_ERROR);
    }
}
