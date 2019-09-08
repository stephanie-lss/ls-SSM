package com.cqupt.ssm.service;

import com.cqupt.ssm.domain.Product;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * Created by Lisheng in 10:23 2019/9/4
 */
public interface IProductService {

    /**
     * 查询所有的产品信息
     *
     * @return
     * @throws Exception
     */
    List<Product> findAll() throws Exception;

//    void delete(Integer id) throws Exception;

    /**
     * 保存产品信息
     * @param product
     */
    void save(Product product);
}

