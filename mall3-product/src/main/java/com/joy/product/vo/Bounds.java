package com.joy.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Joy
 */
@Data
public class Bounds {
    /**
     * 购买积分
     */
    private BigDecimal buyBounds;
    /**
     * 成长积分
     */
    private BigDecimal growBounds;
}
