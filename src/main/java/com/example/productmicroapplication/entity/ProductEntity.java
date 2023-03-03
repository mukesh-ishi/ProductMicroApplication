package com.example.productmicroapplication.entity;



import com.example.productmicroapplication.enums.CategoryEnum;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PRODUCT")
public class ProductEntity implements Serializable {
    @Id
    private String id;

    private String name;

    private CategoryEnum category;

    private BigDecimal price;

    private Integer inStock;

    private Integer reservedQuantity;

    private Boolean available;
}
