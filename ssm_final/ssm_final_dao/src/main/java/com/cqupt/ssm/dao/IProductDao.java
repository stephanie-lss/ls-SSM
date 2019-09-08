package com.cqupt.ssm.dao;

import com.cqupt.ssm.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lisheng in 10:24 2019/9/4
 */
public interface IProductDao {
    /**
     * 查询所有的产品信息
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /**
     * 根据ID查询产品
     * @param id
     * @throws Exception
     */
    @Select("select * from product where id = #{id}")
    Product findById(Integer id) throws Exception;

    /**
     * 保存产品信息
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
