package com.pym.sys.ifs.impl;

import com.pym.base.ResultModel;
import com.pym.sys.ifs.SysUserInterface;
import com.pym.sys.pojo.SysUser;
import com.pym.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/14 19:46
 */
@RestController
public class SysUserController implements SysUserInterface {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public ResultModel<SysUser> getUserById(Long userId) {
        return ResultModel.ok(sysUserService.queryById(userId));
    }

    @Override
    public ResultModel<String> login(String userName, String password) {
        return ResultModel.ok(sysUserService.login(userName, password));
    }
}
