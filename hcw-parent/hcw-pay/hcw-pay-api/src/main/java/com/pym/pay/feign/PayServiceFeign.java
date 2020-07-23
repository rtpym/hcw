package com.pym.pay.feign;

import com.pym.pay.ifs.PayServiceInterface;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/18 23:09
 */
@FeignClient(value = "HCW-PAY-SERVICE")
public interface PayServiceFeign extends PayServiceInterface {
}
