package com.cqupt.ssm.service;

import com.cqupt.ssm.domain.Orders;

import java.util.List;

/**
 * Created by Lisheng in 19:14 2019/9/4
 */
public interface IOrdersService {

    /**
     * 搜索所有订单信息
     * @return
     * @param page
     * @param size
     */
    List<Orders> findAll(Integer page, Integer size) throws Exception;

    /**
     * 根据ID查询订单详情
     * @param id
     * @return
     */
    Orders findById(Integer id) throws Exception;
}
