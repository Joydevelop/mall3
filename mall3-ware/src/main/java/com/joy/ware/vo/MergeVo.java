package com.joy.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Joy
 */
@Data
public class MergeVo {
    private Long purchaseId;
    private List<Long> items;
}
