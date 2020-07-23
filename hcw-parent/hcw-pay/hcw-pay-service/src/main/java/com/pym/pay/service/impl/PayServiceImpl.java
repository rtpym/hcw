package com.pym.pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.github.pagehelper.PageInfo;
import com.pym.base.BaseException;
import com.pym.base.ResultModel;
import com.pym.pay.config.AlipayConfig;
import com.pym.pay.dao.PayInfoDao;
import com.pym.pay.entity.PayInfo;
import com.pym.pay.service.PayService;
import com.pym.pay.util.PayTokenUtils;
import com.pym.utils.ObjUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/18 20:45
 */
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;
    @Autowired
    private PayTokenUtils payTokenUtils;
    @Autowired
    private PayInfoDao payInfoDao;
    @Override
    public PayInfo queryById(Long id) {
        return null;
    }

    @Override
    public boolean update(PayInfo entity) {
        return false;
    }

    @Override
    public boolean create(PayInfo entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public PageInfo<PayInfo> query(Map<String, Object> map) {
        return null;
    }


    @Override
    @Transactional
    public String createToken(PayInfo payInfo) {
        //参数验证
        if (ObjUtils.isEmpty(payInfo) ||
                !ObjUtils.isAllNotEmpty(
                        payInfo.getOrderId(),
                        payInfo.getUserId(),
                        payInfo.getPrice())) {
            throw new BaseException("参数异常");
        }
       Long num = payInfoDao.creatAndGetId(payInfo);
        if (num != 1) {
            throw new BaseException("创建失败");
        }
       String token = payTokenUtils.make();
       //将token写入redis 设置默认缓存时间15min
       payTokenUtils.setToRedis(token, payInfo.getPayId());
       return token;
    }

    @Override
    public PayInfo findByToke(String token) {
        //判空
        if (ObjUtils.isEmpty(token)) {
            throw new BaseException("参数不能为空");
        }
        Long payId = payTokenUtils.getPayIdFromRedis(token);
        if (ObjUtils.isEmpty(payId)) {
            throw new BaseException("订单超时");
        }
        PayInfo payInfo = payInfoDao.queryById(payId);
        if (payInfo == null) {
            throw new BaseException("订单不存在");
        }

        return payInfo;
    }

    @Override
    public PayInfo findByOrderId(Long orderId) {
        if (orderId == null) {
            throw new BaseException("订单编号不能为空");
        }
        PayInfo payInfo = payInfoDao.queryByOrderId(orderId);

        return payInfo;
    }

    @Override
    public boolean dealAlipayNotify(Map<String, String> params) {
        boolean signVerified = false; //调用SDK验证签名
        TransactionStatus ts = null;
        try {
            //1.验签
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            if(signVerified) {
                //验参数
                //商户订单号
                String out_trade_no = params.get("out_trade_no");
                PayInfo payInfo = payInfoDao.queryByOrderId(Long.parseLong(out_trade_no));
                if (payInfo == null) {
                    throw new BaseException("订单不存在");
                }
                //检查订单信息是否已经跟新 幂等操作
                if(payInfo.getPayState() == 1) {
                    return true;
                }
                //支付宝交易号
                String trade_no = params.get("trade_no");

                //付款金额
                String total_amount = params.get("total_amount");
                if (! new DecimalFormat("0.00").format(payInfo.getPrice() * 0.01).equals(total_amount)) {
                    throw new BaseException("金额不一致");
                }
                payInfo.setPlatformPayId(trade_no);
                //开启手动事务
               ts = transactionManager.getTransaction(transactionDefinition);

                //3.修改 pay_info数据库

                //4.修改订单是数据库

                //5修改积分数据库

                //6发邮件通知

                //提交
                transactionManager.commit(ts);
                return true;
            }
            throw new BaseException("验签失败");

        } catch (Exception e) {
            if (ts != null) {
                //异常回滚
                transactionManager.rollback(ts);
            }
            throw new BaseException("验签异常");
        }
    }
}
