package com.cqupt.ssm.service.impl;

import com.cqupt.ssm.dao.IProductDao;
import com.cqupt.ssm.domain.Product;
import com.cqupt.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Lisheng in 10:26 2019/9/4
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;
    /**
     * 查询所有的产品信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findAll() throws Exception {
        List<Product> products = productDao.findAll();
        return products;
    }

    /**
     * 保存产品信息
     *
     * @param product
     */
    @Override
    public void save(Product product) {
        productDao.save(product);
    }

//    @Override
//    public void deleteById(String id) throws Exception {
//        productDao.deleteById(id);
//    }


}
