package com.joy.product.vo;

import lombok.Data;

/**
 * @author Joy
 */
@Data
public class BaseAttrs {
    private Long attrId;
    private String attrValues;
    /**
     * 是否快速展示
     */
    private int showDesc;
}
