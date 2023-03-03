package com.example.productmicroapplication.controller;


import com.example.productmicroapplication.DTOs.ProductDTO;
import com.example.productmicroapplication.DTOs.ReserveProductDTO;
import com.example.productmicroapplication.entity.ProductEntity;
import com.example.productmicroapplication.exception.ProductNotFoundException;
import com.example.productmicroapplication.exception.ProductOutOFStockException;
import com.example.productmicroapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductEntity product)throws ProductNotFoundException {

        productService.saveProduct(product);
        return new ResponseEntity<>("PRODUCT ADDED", HttpStatus.valueOf(201));

    }
    @PostMapping("/addAll")
    public ResponseEntity<String> addAllProduct(@RequestBody List<ProductEntity> productEntityList)throws ProductNotFoundException {
        productService.saveAllProduct(productEntityList);
        return new ResponseEntity<>("All PRODUCT ADDED", HttpStatus.valueOf(201));

    }



    @GetMapping("getById/{ID}")
    public ResponseEntity<ProductDTO> fetchProductById(@PathVariable String ID) throws Exception {
        ProductDTO product=productService.getProductById(ID);
        return new ResponseEntity<>(product,HttpStatus.OK);

    }
    @PutMapping("/update/{ID}/{reserveQauntity}")
    public ResponseEntity<ReserveProductDTO> modifyProductDetail(@PathVariable(name="ID") String ID, @PathVariable(name="reserveQauntity") Integer reserveQauntity) throws ProductOutOFStockException {
        ReserveProductDTO reserveProductDTO=productService.updateProductDetail(ID,reserveQauntity);
        return new ResponseEntity<>(reserveProductDTO,HttpStatus.OK);
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        List<ProductDTO> productDTOList=productService.getALLProducts();
        return new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{ID}")
    public ResponseEntity<String> deleteProductById(@PathVariable String ID){
        try {
            String message = productService.deleteById(ID);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
