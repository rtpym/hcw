package com.pym.sys.feign;

import com.pym.sys.feign.fallback.SysUserFeignFallbackFactory;
import com.pym.sys.ifs.SysUserInterface;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/15 1:52
 */
@FeignClient(value = "HCW-SYS-SERVICE", fallbackFactory = SysUserFeignFallbackFactory.class)
public interface SysUserFeign extends SysUserInterface {
}
