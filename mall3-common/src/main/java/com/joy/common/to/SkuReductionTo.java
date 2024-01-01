package com.joy.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Joy
 */
@Data
public class SkuReductionTo {
    private Long skuId;

    private int fullCount;

    private BigDecimal discount;

    private int countStatus;

    private BigDecimal fullPrice;

    private BigDecimal reducePrice;

    private int priceStatus;

    private List<MemberPrice> memberPrice;
}
