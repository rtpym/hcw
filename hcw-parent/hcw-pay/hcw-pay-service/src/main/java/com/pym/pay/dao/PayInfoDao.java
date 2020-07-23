package com.pym.pay.dao;

import com.pym.pay.entity.PayInfo;
import org.apache.ibatis.annotations.*;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/18 21:09
 */
@Mapper
public interface PayInfoDao {
   @Insert("insert into pay_info (payId,orderId,price,userId) values (null,#{orderId}, #{price}, #{userId})")
   @Options(useGeneratedKeys = true, keyProperty = "payId")
   Long creatAndGetId(PayInfo payInfo);
   @Select("select * from pay_info where payId=#{payId}")
   PayInfo queryById(@Param("payId") Long payId);
   @Select("select * from pay_info where orderId=#{orderId} ")
   PayInfo queryByOrderId(@Param("orderId") Long orderId);
}
