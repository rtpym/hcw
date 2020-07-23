package com.pym.sys.service;

import com.pym.base.BaseService;
import com.pym.sys.pojo.SysUser;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/14 16:43
 */
public interface SysUserService extends BaseService<SysUser> {
    SysUser queryByUserName(String userName);
    String login(String userName, String password);
}
