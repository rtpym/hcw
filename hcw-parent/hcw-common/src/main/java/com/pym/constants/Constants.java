package com.pym.constants;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/14 23:37
 */
public interface Constants {

    String TOKEN_TAG = "HCW";//token前缀
    String TOKEN_PAY_TAG = "HCW-PAY-";//支付token前缀
    String TOKEN_COOKIE_NAME = "TOKEN_COOKIE";//存放的cookie名
    String ENCODE_SALT_DEFAULT = "HCW"; //加密算法中默认加的盐
    String LOGIN_ERROR_NUM_REDIS_KEY_TAG = "LOGIN-ERROR-NUM-";//登录错误次数redis key前缀
    int MAX_LOGIN_ERROR_NUM = 5;
    long ACCOUNT_LOCK_TIME = 60 * 60 * 24;


    //-------------------- 正则 ------------------------------
    String REG_PHONE = "^1[345789]\\d{9}$";//手机正则
    String REG_EMAIL = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";//邮箱正则
    String REG_QQ = "^[1-9][0-9]{5,10}"; //qq号正则
    String REG_USER_NAME = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";//8-16位用户名
    String REG_PASSWORD = "^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?!([^(0-9a-zA-Z)])+$).{6,20}$";//6-20位密码

}
