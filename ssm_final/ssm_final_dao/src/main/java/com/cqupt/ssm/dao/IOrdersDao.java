package com.cqupt.ssm.dao;

import com.cqupt.ssm.domain.Member;
import com.cqupt.ssm.domain.Orders;
import com.cqupt.ssm.domain.Product;
import com.cqupt.ssm.domain.Traveller;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Lisheng in 19:23 2019/9/4
 */
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", javaType = Product.class, one = @One(select = "com.cqupt.ssm.dao.IProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", javaType = Product.class, one = @One(select = "com.cqupt.ssm.dao.IProductDao.findById")),
            @Result(column = "memberId",property = "member",javaType = Member.class,one = @One(select = "com.cqupt.ssm.dao.IMemberDao.findById")),
            @Result(column = "id",property = "travellers",javaType = List.class,many = @Many(select = "com.cqupt.ssm.dao.ITravellerDao.findById"))
    })
    Orders findById(Integer id) throws Exception;
}
