package com.pym.base;

import com.alibaba.fastjson.JSON;
import com.pym.constants.ResultMsgEnum;
import com.pym.constants.ResultCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * TODO
 *  响应模型
 * @author zhangping
 * @version 1.0
 * @date 2020/7/14 13:08
 */
@Setter
@Getter
@Accessors(chain = true)
public class ResultModel<T> implements Serializable {
    private int code;
    private T data;
    private String msg;

    public static ResultModel ok() {
        return msgOk("SUCCESS");
    }
    /**
     * 成功 带消息模型
     * @param msg
     * @return
     */
    public static ResultModel msgOk(String msg) {
        return model(ResultCode.CODE_SUCCESS, msg, null);
    }

    /**
     * 成功 带数据模型
     * @param data
     * @param <E>
     * @return
     */
    public static <E> ResultModel<E> ok(E data) {
        return model(ResultCode.CODE_SUCCESS, null, data);
    }
    public static ResultModel fail(){
        return msgFail("FAIL");
    }
    /**
     * 失败 带消息模型
     * @param msg
     * @return ResultModel
     */
    public static ResultModel msgFail(String msg) {
        return model(ResultCode.CODE_FAIL, msg, null);
    }

    /**
     * 失败 带数据模型
     * @param data
     * @param <E>
     * @return
     */
    public static <E> ResultModel<E> fail(E data) {
        return model(ResultCode.CODE_FAIL, null, data);
    }

    /**
     * 消息模型
     * @param code
     * @param msg
     * @return
     */
    public static ResultModel msgModel(int code, String msg) {
        return model(code,msg,null);
    }
    /* *
     * @Description
     * 消息模型
     * @Param [resultMsgEnum]
     * @return com.pym.base.ResultModel
     * @Author zhangping
     * @Date 2020/7/14 15:54
     **/
    public static ResultModel msgModel(ResultMsgEnum resultMsgEnum) {
        return msgModel(resultMsgEnum.getCode(), resultMsgEnum.getMsg());
    }
    /**
     * 数据模型
     * @param code
     * @param data
     * @param <E>
     * @return
     */
    public static <E> ResultModel<E> model(int code, E data) {
        return model(code, null, data);
    }

    /**
     * 状态码 消息 数据 模型
     * @param code
     * @param msg
     * @param data
     * @param <E>
     * @return
     */
    public static <E> ResultModel<E> model(int code, String msg, E data) {
        return new ResultModel<E>().setCode(code).setData(data).setMsg(msg);
    }

    /**
     * 模型转json
     * @return
     */
    public String toJson() {
        return JSON.toJSONString(this);
    }

}
