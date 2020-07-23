package com.pym.sys.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *  用户实体类
 * @author zhangping
 * @version 1.0
 * @date 2020/7/14 11:20
 */
@Getter
@Setter
@Data
@Accessors(chain = true)
public class SysUser implements Serializable {
    private Long userId;
    private Byte userType;//0内置 1普通用户
    private String userName;
    private String password;
    private String phone;
    private String desc;
    private String qqOpenId; //支持qq登录
    private String wxOpenId; //支持微信登录
    private Date createdTime;
    private Date updatedTime;
}
