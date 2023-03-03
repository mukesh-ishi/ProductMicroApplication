package com.example.productmicroapplication.utils;




import com.example.productmicroapplication.DTOs.ProductDTO;
import com.example.productmicroapplication.DTOs.ReserveProductDTO;
import com.example.productmicroapplication.entity.ProductEntity;
import com.example.productmicroapplication.enums.CategoryEnum;

import java.math.RoundingMode;

public class MapperUtils {

    public static ProductDTO toProductDTO(ProductEntity productEntity) {
        return ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .category(productEntity.getCategory().toString())
                .price(productEntity.getPrice().setScale(2, RoundingMode.CEILING))
                .inStock(productEntity.getInStock())
                .reservedQuantity(productEntity.getReservedQuantity())
                .available(productEntity.getAvailable())
                .build();
    }

    public static ReserveProductDTO toReserveProductDTO(ProductEntity  productEntity){
        return ReserveProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .category(productEntity.getCategory().toString())
                .price(productEntity.getPrice().setScale(2, RoundingMode.CEILING))
                .inStock(productEntity.getInStock())
                .reservedQuantity(productEntity.getReservedQuantity())
                .available(productEntity.getAvailable())
                .build();
    }

    public static ProductEntity toProductEntity(ProductDTO productDTO){
        return ProductEntity.builder()
                .name(productDTO.getName())
                .category(CategoryEnum.valueOf(productDTO.getCategory().toString()))
                .price(productDTO.getPrice().setScale(2, RoundingMode.CEILING))
                .inStock(productDTO.getInStock())
                .reservedQuantity(productDTO.getReservedQuantity())
                .available(productDTO.getAvailable())
                .build();
    }



    private MapperUtils() {}
}
