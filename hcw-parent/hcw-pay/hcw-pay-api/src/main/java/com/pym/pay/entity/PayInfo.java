package com.pym.pay.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/18 18:58
 */
@Setter
@Getter
@Accessors(chain = true)
public class PayInfo {
    private Long payId; //支付id
    private Integer payType;//
    private Long orderId;//订单id
    private Long userId; //用户id
    private Integer price; //价格分为单位
    private String platformPayId;//第三方支付id
    private String payMsg;//第三方支付信息
    private Integer payState;//支付状态 0未支付1已支付2支付失败
    private Date updatedTime;
    private Date createdTime;

}
