package com.joy.product.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Joy
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttrRespVo extends AttrVo{
    private String catelogName;
    private String groupName;
    private Long[] catelogPath;
}
