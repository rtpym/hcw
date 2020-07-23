package com.pym.sys.service.impl;

import com.github.pagehelper.PageInfo;
import com.pym.base.BaseException;
import com.pym.constants.Constants;
import com.pym.constants.ResultMsgEnum;
import com.pym.sys.dao.SysUserDao;
import com.pym.sys.pojo.SysUser;
import com.pym.sys.service.SysUserService;
import com.pym.utils.EncryptUtils;
import com.pym.utils.ObjUtils;
import com.pym.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/14 16:54
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser queryById(Long id) {
        if (id == null) {
            throw new BaseException("用户id不能为空");
        }
        return sysUserDao.queryById(id).setPassword(null);
    }

    @Override
    public SysUser queryByUserName(String userName) {
        if (ObjUtils.isEmpty(userName)) {
            throw new BaseException("用户名不能为空");
        }
        return sysUserDao.queryByUserName(userName).setPassword(null);
    }
    /* *
     * @Description
     *  用户登录 失败后增加错误次数 成功后生成token 将token-sysUser 存入redis
     * @Param [userName, password]
     * @return java.lang.String
     * @Author zhangping
     * @Date 2020/7/15 1:34
     **/
    @Override
    public String login(String userName, String password) {
        //检查数据
        if (!ObjUtils.isAllNotEmpty(userName,password)) {
            throw new BaseException(ResultMsgEnum.LOGIN_ERROR_NULL);
        }
        //检查缓存中的错误次数
        String key = Constants.LOGIN_ERROR_NUM_REDIS_KEY_TAG + userName;
        String numStr = redisTemplate.opsForValue().get(key);
        int num = 0;
        if (!ObjUtils.isEmpty(numStr)) {
            num = Integer.parseInt(numStr);
            //次数达到默认值
            if (num >= Constants.MAX_LOGIN_ERROR_NUM) {
                 long time = redisTemplate.getExpire(key) / 3600;
                throw new BaseException("登录尝试次数过多，账号已封锁，请联系管理员或者在"+time+"小时后重试！");
            }
        }
        // 查询数据库
        SysUser sysUser = sysUserDao.queryByUserName(userName);
        if (ObjUtils.isEmpty(sysUser)) {
            throw new BaseException(ResultMsgEnum.LOGIN_ERROR_NOT_EXITS);
        }
        // 比较密码
        //密码不正确
        if (!EncryptUtils.base64Encode(password, Constants.ENCODE_SALT_DEFAULT)
                .equals(sysUser.getPassword())) {
            //错误次数加1
            redisTemplate.opsForValue().set(key, (num + 1) + "", Constants.ACCOUNT_LOCK_TIME, TimeUnit.SECONDS);
            //提示
            throw new BaseException(ResultMsgEnum.LOGIN_ERROR);
        }
        //密码正确
        //消除错误次数
        redisTemplate.delete(key);
        //生成token
        String token = TokenUtils.make();
        //将token放到跟用户信息放到缓存
        sysUser.setPassword(null);
        redisTemplate.opsForValue().set(token, ObjUtils.toJson(sysUser));
        return token;
    }

    @Override
    @Transactional
    public boolean update(SysUser entity) {
        return false;
    }

    @Override
    @Transactional
    public boolean create(SysUser entity) {
        return false;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public PageInfo<SysUser> query(Map<String, Object> map) {
        return null;
    }
}
