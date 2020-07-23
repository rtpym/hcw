package com.pym.pay.util;

import com.pym.constants.Constants;
import com.pym.utils.ObjUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/18 20:16
 */
@Component
public class PayTokenUtils {
    public static final long DEFAULT_EXPIRE_TIME = 60 * 15;//默认15分钟过期
    @Autowired
    private  StringRedisTemplate redisTemplate;

    /* *
     * @Description
     * 生成支付令牌
     * @Param []
     * @return java.lang.String
     * @Author zhangping
     * @Date 2020/7/18 20:17
     **/
    public String make() {
        return Constants.TOKEN_PAY_TAG + UUID.randomUUID().toString();
    }
    /* *
     * @Description
     * 存入redis
     * @Param [payToken, payId, time]
     * @return java.lang.String
     * @Author zhangping
     * @Date 2020/7/18 20:29
     **/
    public void setToRedis(String payToken, Long payId, Long time) {
        redisTemplate.opsForValue().set(payToken,payId.toString(),time, TimeUnit.SECONDS);
    }
    public void setToRedis(String payToken, Long payId) {
        //redisTemplate.opsForValue().set(payToken,payId.toString(),time, TimeUnit.SECONDS);
        setToRedis(payToken,payId,DEFAULT_EXPIRE_TIME);
    }
    /* *
     * @Description
     * 从redis中获取支付id
     * @Param [payToken]
     * @return java.lang.Long
     * @Author zhangping
     * @Date 2020/7/18 20:37
     **/
    public Long getPayIdFromRedis(String payToken) {
        String payIdStr = redisTemplate.opsForValue().get(payToken);
        return ObjUtils.isEmpty(payIdStr)? null : Long.parseLong(payIdStr);
    }
}
