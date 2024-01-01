package com.joy.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Joy
 */
@Data
public class SpuBoundTo {
    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;

}
