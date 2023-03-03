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
public class ReserveProductDTO implements Serializable {
    private String id;
    private String name;
    private String category;

    private BigDecimal price;

    @JsonProperty("inStock")
    private Integer inStock;

    @JsonProperty("reservedQuantity")
    private Integer reservedQuantity;

    @JsonProperty("available")
    private Boolean available;
}
