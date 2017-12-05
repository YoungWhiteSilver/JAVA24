package com.kaishengit.service.impl;

import com.kaishengit.entity.Product;
import com.kaishengit.example.ProductExample;
import com.kaishengit.mappers.ProductMapper;
import com.kaishengit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/5
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> findAll() {

        ProductExample productExample = new ProductExample();
        productExample.setOrderByClause("end_time desc");
        return productMapper.selectByExample(productExample);

    }


}
