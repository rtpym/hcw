package com.pym.sys.feign.fallback;

import com.pym.base.ResultModel;
import com.pym.sys.feign.SysUserFeign;
import com.pym.sys.pojo.SysUser;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/19 15:26
 */
@Component
public class SysUserFeignFallbackFactory implements FallbackFactory<SysUserFeign> {
    @Override
    public SysUserFeign create(Throwable throwable) {
        return new SysUserFeign() {
            @Override
            public ResultModel<SysUser> getUserById(Long userId) {
                return ResultModel.msgFail("服务降级");
            }

            @Override
            public ResultModel<String> login(String userName, String password) {
                return ResultModel.msgFail("服务降级");
            }
        };
    }
}
