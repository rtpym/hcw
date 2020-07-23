package com.pym.pay.ifs;

import com.pym.base.ResultModel;
import com.pym.pay.entity.PayInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/18 20:41
 */
@RequestMapping("/pay")
public interface PayServiceInterface {
    /* *
     * @Description
     * 创建paytoken
     * @Param [payInfo]
     * @return com.pym.base.ResultModel
     * @Author zhangping
     * @Date 2020/7/18 21:01
     **/
    @PostMapping("/token/create")
    ResultModel createPayToken(@RequestBody PayInfo payInfo);
    /* *
     * @Description
     * 根据token获取支付信息
     * @Param [payToken]
     * @return com.pym.base.ResultModel
     * @Author zhangping
     * @Date 2020/7/18 21:01
     **/
    @GetMapping("/alipaypage/get/{payToken}")
    ResultModel getAlipayPageByToke(@PathVariable("payToken") String payToken);
    /* *
     * @Description
     * 支付宝同步回调
     * @Param [params]
     * @return com.pym.base.ResultModel
     * @Author zhangping
     * @Date 2020/7/19 1:18
     **/
    @PostMapping("/alipay/return")
    ResultModel alipayReturn(@RequestBody Map<String, String> params);
    /* *
     * @Description
     * 支付宝异步通知
     * @Param [params]
     * @return com.pym.base.ResultModel
     * @Author zhangping
     * @Date 2020/7/19 1:19
     **/
    @PostMapping("/alipay/notify")
    ResultModel alipayNotify(@RequestParam Map<String, String> params);
}
