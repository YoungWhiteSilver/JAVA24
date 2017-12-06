package com.kaishengit.service;

import com.kaishengit.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/12/5
 */
public interface ProductService {


    /**
     * 查找所有
     * @return
     */
    List<Product> findAll();

    /**
     * 保存商品并上传
     * @param product
     * @param file
     */
    void saveProduct(Product product, MultipartFile file);

    /**
     * 查找商品通过Id
     * @param id
     * @return
     */
    Product findById(Integer id);

    /**
     * 秒杀照片
     * @param id
     */
    void spikeProduct(Integer id);
}
