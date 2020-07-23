package com.pym.base;

import com.pym.constants.ResultMsgEnum;
import com.pym.constants.ResultCode;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 *  异常基类
 * @author zhangping
 * @version 1.0
 * @date 2020/7/14 15:08
 */
@Getter
@Setter
public class BaseException extends RuntimeException {
    private int code;
    private String msg;
    public BaseException() {
        super();
        this.code = ResultCode.CODE_FAIL;
        this.msg = "系统异常";
    }
    public BaseException(Throwable e) {
        super(e);
    }
    public BaseException(ResultMsgEnum resultMsgEnum) {
        this(resultMsgEnum,null);
    }
    public BaseException(ResultMsgEnum resultMsgEnum, Throwable e) {
        super(resultMsgEnum.getMsg(), e);
        this.code = resultMsgEnum.getCode();
        this.msg = resultMsgEnum.getMsg();
    }

    public BaseException(String msg) {
        this(msg,null);
    }
    public BaseException(String msg, Throwable e) {
        super(msg,e);
        this.msg = msg;
        this.code = ResultCode.CODE_FAIL;
    }
}
