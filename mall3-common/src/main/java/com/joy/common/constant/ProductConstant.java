package com.joy.common.constant;

/**
 * @author Joy
 */
public class ProductConstant {
    public enum AttrEnum {
        ATTR_TYPE_BASE(1, "基本属性"), ATTR_TYPE_SALE(0, "销售属性");

        private int code;
        private String msg;

        AttrEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static AttrEnum getAttrEnumByCode(int code) {
            AttrEnum[] values = AttrEnum.values();
            for (AttrEnum value : values) {
                if (value.getCode() == code) {
                    return value;
                }
            }
            return null;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
