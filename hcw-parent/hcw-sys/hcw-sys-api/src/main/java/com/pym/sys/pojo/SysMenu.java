package com.pym.sys.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *  菜单实体类
 * @author zhangping
 * @version 1.0
 * @date 2020/7/14 11:37
 */
@Getter
@Setter
@Data
@Accessors(chain = true)
public class SysMenu implements Serializable {
    private Long menuId;
    private String menuType; //0内置 1普通
    private String menuName;
    private Byte isParent; //是否为父节点
    private Long parentId; //父节点id
    private String menuRul; //菜单对应的链接地址
    private String desc;
    private String permissions;//菜单需要的访问权限只控制到角色 如read:all; write:admin:
    private Date createdTime;
    private Date updatedTime;
}
