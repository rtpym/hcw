package com.pym.sys.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *  角色实体类
 * 内置角色：
 *  1.root 创建内置用户并分配权限
 *  2.sysadmin 上传产品信息、修改功能
 *  3.admin 用户账号管理，授权、解封等
 *  4.audit 管理用户操作日志
 * @author zhangping
 * @version 1.0
 * @date 2020/7/14 11:30
 */
@Getter
@Setter
@Data
@Accessors(chain = true)
public class SysRole implements Serializable {
    private Long roleId;
    private Byte roleType;//0内置 1普通
    private String roleName;
    private String desc;
    private Date createdTime;
    private Date updatedTime;
}
