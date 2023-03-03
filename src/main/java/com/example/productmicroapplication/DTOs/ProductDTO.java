package com.example.productmicroapplication.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
@Setter
@Getter
@Builder
public class ProductDTO implements Serializable {
    private String id;

    private String name;

    private String category;

    private BigDecimal price;

    @JsonProperty("in_stock")
    private Integer inStock;

    @JsonProperty("reserved_quantity")
    private Integer reservedQuantity;

    @JsonProperty("is_available")
    private Boolean available;
}
