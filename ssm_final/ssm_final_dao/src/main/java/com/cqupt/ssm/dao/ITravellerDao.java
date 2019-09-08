package com.cqupt.ssm.dao;

import com.cqupt.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Lisheng in 10:28 2019/9/5
 */
public interface ITravellerDao {

    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId=#{id})")
    List<Traveller> findById(Integer id) throws Exception;
}
