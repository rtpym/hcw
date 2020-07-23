package com.pym.pay.ifs.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.pym.base.BaseException;
import com.pym.base.ResultModel;
import com.pym.pay.config.AlipayConfig;
import com.pym.pay.entity.PayInfo;
import com.pym.pay.ifs.PayServiceInterface;
import com.pym.pay.service.PayService;
import com.pym.utils.ObjUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/18 21:58
 */
@RestController
public class PayController implements PayServiceInterface {
    @Autowired
    private PayService payService;
    @Override
    public ResultModel createPayToken(PayInfo payInfo) {
        return ResultModel.ok(payService.createToken(payInfo));
    }

    @Override
    public ResultModel getAlipayPageByToke(String payToken) {
        PayInfo payInfo = payService.findByToke(payToken);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = payInfo.getOrderId().toString();
        //付款金额，必填
        String total_amount = new DecimalFormat("0.00").format(payInfo.getPrice() * 0.01);
        //订单名称，必填
        String subject = "我忘了写";
        //商品描述，可空
        String body = "";
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");


        //请求
        try {
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            return ResultModel.ok(result);
        } catch (AlipayApiException e) {
            throw new BaseException("支付异常");
        }

    }

    @Override
    public ResultModel alipayReturn(Map<String, String> params) {
        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            //——请在这里编写您的程序（以下代码仅作参考）——
            if(signVerified) {
                //商户订单号
                String out_trade_no = params.get("out_trade_no");
                PayInfo payInfo = payService.findByOrderId(Long.parseLong(out_trade_no));
                if (payInfo == null) {
                    return ResultModel.msgFail("订单不存在");
                }
                //支付宝交易号
                String trade_no = params.get("trade_no");

                //付款金额
                String total_amount = params.get("total_amount");
                if (! new DecimalFormat("0.00").format(payInfo.getPrice() * 0.01).equals(total_amount)) {
                    return ResultModel.msgFail("金额不一致");
                }
                payInfo.setPlatformPayId(trade_no);
                return ResultModel.ok(payInfo);
            }
            return ResultModel.msgFail("验签失败");

        } catch (AlipayApiException e) {
            return ResultModel.msgFail("验签异常");
        }


    }

    @Override
    public ResultModel alipayNotify(Map<String, String> params) {
        boolean signVerified = false; //调用SDK验证签名
        try {
            //1.验签
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            if(signVerified) {
                //验参数
                //商户订单号
                String out_trade_no = params.get("out_trade_no");
                PayInfo payInfo = payService.findByOrderId(Long.parseLong(out_trade_no));
                if (payInfo == null) {
                    return ResultModel.msgFail("订单不存在");
                }
                //支付宝交易号
                String trade_no = params.get("trade_no");

                //付款金额
                String total_amount = params.get("total_amount");
                if (! new DecimalFormat("0.00").format(payInfo.getPrice() * 0.01).equals(total_amount)) {
                    return ResultModel.msgFail("金额不一致");
                }
                payInfo.setPlatformPayId(trade_no);
                //3.修改 pay_info数据库
                //4.修改订单是数据库
                //5修改积分数据库
                //6发邮件通知
                return ResultModel.ok("SUCCESS");
            }
            return ResultModel.msgFail("验签失败");

        } catch (AlipayApiException e) {
            return ResultModel.msgFail("验签异常");
        }
    }


}
