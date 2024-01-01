package com.joy.common.constant;

/**
 * @author Joy
 */
public class WareConstant {
    public enum PurchaseStatusEnum {
        /**
         * 新建
         */
        CREATED(0, "新建"),
        /**
         * 已分配
         */
        ASSIGNED(1, "已分配"),
        /**
         * 已领取
         */
        RECEIVED(2, "已领取"),
        /**
         * 已完成
         */
        FINISHED(3, "已完成"),
        /**
         * 采购失败
         */
        FAILED(4, "采购失败"),
        /**
         * 采购成功
         */
        SUCCESS(5, "采购成功"),
        /**
         * 采购取消
         */
        CANCEL(6, "采购取消");

        private final int code;
        private final String msg;

        PurchaseStatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static PurchaseStatusEnum getEnum(int code) {
            for (PurchaseStatusEnum purchaseStatusEnum : PurchaseStatusEnum.values()) {
                if (purchaseStatusEnum.getCode() == code) {
                    return purchaseStatusEnum;
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

    public enum PurchaseDetailStatusEnum {
        /**
         * 新建
         */
        CREATED(0, "新建"),
        /**
         * 已分配
         */
        ASSIGNED(1, "已分配"),
        /**
         * 已领取
         */
        BUYING(2, "正在采购"),
        /**
         * 已完成
         */
        FINISHED(3, "已完成"),
        /**
         * 采购失败
         */
        FAILED(4, "采购失败");

        private final int code;
        private final String msg;

        PurchaseDetailStatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static PurchaseDetailStatusEnum getEnum(int code) {
            for (PurchaseDetailStatusEnum purchaseDetailStatusEnum : PurchaseDetailStatusEnum.values()) {
                if (purchaseDetailStatusEnum.getCode() == code) {
                    return purchaseDetailStatusEnum;
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
