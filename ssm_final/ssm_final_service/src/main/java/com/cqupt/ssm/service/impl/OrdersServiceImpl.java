package com.cqupt.ssm.service.impl;

import com.cqupt.ssm.dao.IOrdersDao;
import com.cqupt.ssm.domain.Orders;
import com.cqupt.ssm.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lisheng in 19:15 2019/9/4
 */
@Service("ordersService")
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;
    /**
     * 查询所有订单信息
     *
     * @return
     * @param page
     * @param size
     */
    @Override
    public List<Orders> findAll(Integer page, Integer size) throws Exception{
        PageHelper.startPage(page,size);
        List<Orders> orders= ordersDao.findAll();
        return orders;
    }

    /**
     * 根据ID查询订单详情
     *
     * @param id
     * @return
     */
    @Override
    public Orders findById(Integer id) throws Exception {
        return ordersDao.findById(id);
    }
}
