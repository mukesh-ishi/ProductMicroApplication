package com.example.productmicroapplication.service;



import com.example.productmicroapplication.DTOs.ProductDTO;
import com.example.productmicroapplication.DTOs.ReserveProductDTO;
import com.example.productmicroapplication.entity.ProductEntity;
import com.example.productmicroapplication.exception.ProductNotFoundException;
import com.example.productmicroapplication.exception.ProductOutOFStockException;
import com.example.productmicroapplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.productmicroapplication.utils.MapperUtils.toProductDTO;
import static com.example.productmicroapplication.utils.MapperUtils.toReserveProductDTO;
import static com.example.productmicroapplication.utils.RandomGeneratorUtils.generateRandomUUID;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        productEntity.setId(generateRandomUUID());
        productEntity.setReservedQuantity(0);
        productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public String saveAllProduct(List<ProductEntity> productEntityList) {

        for (ProductEntity productEntity:productEntityList) {
            productEntity.setId(generateRandomUUID());
            productEntity.setReservedQuantity(0);

        }
        Iterable<ProductEntity> savedAllProduct=productRepository.saveAll(productEntityList);
        List<String> ids=new ArrayList<>();
        if(savedAllProduct!=null && ((List<ProductEntity>)savedAllProduct).size()>0)
            savedAllProduct.forEach(s->{
                ids.add(s.getId());
            });
        return ids.toString()+"All product added";
    }


    @Override
//    @Cacheable(key="#ID",value="ProductEntity")
    public ProductDTO getProductById(String ID) throws Exception {

            Optional<ProductEntity> product = productRepository.findById(ID);


            ProductEntity productEntity = product.get();

            if (product.isPresent()) {
                ProductDTO productDTO = toProductDTO(productEntity);
                return productDTO;

            } else {
                throw new ProductNotFoundException(ID + "Product not found");
            }
        }



    @Override
//    @CachePut(key = "#Id", value = "ProductEntity")
    public ReserveProductDTO updateProductDetail(String ID, Integer reserveQauntity) throws ProductOutOFStockException {
        Optional<ProductEntity> product = productRepository.findById(ID);

       ProductEntity product1 = product.get();
       Integer inStock = product1.getInStock();
       Integer newReserveQauntity = product1.getReservedQuantity() + reserveQauntity;
      if (product1.getInStock().equals(0)) {
            new ProductOutOFStockException("PRODUCT OUT OF STOCK!!");
        }
        if (product1.getInStock() > reserveQauntity) {
            product1.setReservedQuantity(newReserveQauntity);
            product1.setInStock(inStock - reserveQauntity);
        }
        productRepository.save(product1);
        ReserveProductDTO reserveProductDTO = toReserveProductDTO(product1);
        return reserveProductDTO;
    }



    @Override
//    @Cacheable("ProductEntity")
    public List<ProductDTO> getALLProducts() {
        List<ProductEntity> productEntityList=productRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();

        for (ProductEntity productEntity: productEntityList) {
            ProductDTO product = toProductDTO(productEntity);
            products.add(product);
        }
        return products;
    }

    @Override
//    @CacheEvict(key = "#Id", value = "ProductEntity", allEntries=true)
    public String deleteById(String ID)throws ProductNotFoundException {
        Optional<ProductEntity> product = productRepository.findById(ID);

               ProductEntity productEntity = product.get();
               productRepository.delete(productEntity);
               return ID + "PRODUCT DELETED";

    }

}
