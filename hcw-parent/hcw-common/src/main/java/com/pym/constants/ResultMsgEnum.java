package com.pym.constants;

public enum ResultMsgEnum {
    SUCCESS(200,"成功"),
    BODY_NOT_MATCH(400,"请求的数据格式不符!"),
    LOGIN_ERROR_NULL(401, "用户名或密码不能为空"),
    LOGIN_ERROR_NOT_EXITS(403, "用户不存在"),
    LOGIN_ERROR(402, "用户或密码不正确"),
    LOGIN_ERROR_NUM_MAX(405,"登录错误次数过多，账号被封锁，请联系管理员"),
    NOT_FOUND(404, "未找到该资源!"),
    SERVER_ERROR(500, "系统异常!"),
    SERVER_BUSY(503,"服务器正忙，请稍后再试!");

    private int code;
    private String msg;
    ResultMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return this.code;
    }
    public String getMsg() {
        return this.msg;
    }

}
