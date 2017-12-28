package com.park.constants;

/**
 * 状态常量类
 * Created by zzy on 2017/12/20.
 */
public interface PayConstants {

    /**
     * pay_park表支付状态
     */
    public static interface PayParkPayState{

        /**
         * 未支付
         */
        public static final int UN_PAY = 0;

        /**
         * 已支付
         */
        public static final int PAID = 1;

    }

    /**
     * 停车场类型
     */
    public static interface ParkType {
        /**
         * 道闸停车场
         */
        public static final int GATE_PARK = 0;
        /**
         * 占道停车场
         */
        public static final int JEEVES_PARK = 1;
        /**
         * 2：露天立体车库停车场
         */
        public static final int OUTDOOR_PARK = 2;
    }

    /**
     * 订单是否完成或者逃逸
     */
    public static interface IsOver{
        /**
         * 未完成
         */
        public static final int UN_OVER = 0;
        /**
         * 完成
         */
        public static final int OVER = 1;
        /**
         * 车辆逃跑
         */
        public static final int RUN_AWAY = 2;
        /**
         * 未交费
         */
        public static final int UN_PAY_OVER = 3;
        /**
         * PDA 补交欠费已完成
         */
        public static final int PDA_OVER = 4;
        /**
         * 5：PDA补交逃逸已完成
         */
        public static final int PDA_RUANAWAY_OVER = 5;
    }
}
