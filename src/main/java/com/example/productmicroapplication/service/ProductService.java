package com.example.productmicroapplication.service;


import com.example.productmicroapplication.DTOs.ProductDTO;
import com.example.productmicroapplication.DTOs.ReserveProductDTO;
import com.example.productmicroapplication.entity.ProductEntity;
import com.example.productmicroapplication.exception.ProductNotFoundException;
import com.example.productmicroapplication.exception.ProductOutOFStockException;

import java.util.List;

public interface ProductService {
    ProductEntity saveProduct(ProductEntity productEntity);
    String saveAllProduct(List<ProductEntity> productEntityList);
    ProductDTO getProductById(String ID) throws Exception;
    ReserveProductDTO updateProductDetail(String ID, Integer reserveQuantity) throws ProductOutOFStockException;
    List<ProductDTO> getALLProducts();
    String deleteById(String ID) throws ProductNotFoundException, ProductNotFoundException;


}
