package com.cqupt.ssm.controller;

import com.cqupt.ssm.domain.Product;
import com.cqupt.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * Created by Lisheng in 10:56 2019/9/4
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {
        System.out.println("调用了");
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList", ps);
        mv.setViewName("product-list1");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Product product){
        System.out.println("web层的save方法执行了");
        productService.save(product);
        return "redirect:findAll";
    }

    /*@RequestMapping("/deleteById")
    public String deleteById() throws Exception {
        productService.deleteById("9F71F01CB448476DAFB309AA6DF9497F");
        return "redirect:findAll";
    }*/
}
