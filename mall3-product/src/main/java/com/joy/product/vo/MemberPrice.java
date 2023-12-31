package com.joy.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Joy
 */
@Data
public class MemberPrice {
    private Long id;
    private String name;
    private BigDecimal price;
}
