package com.park.util;

import java.math.BigDecimal;

/**
 * 金额相关计算工具类
 *
 * @author Peter Wu
 */
public class MoneyUtil {

  /**
   * @param number 单位分
   * @return 单位元
   */
  public static BigDecimal toYun(long number) {
    return new BigDecimal(number).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
  }

  /**
   * @param number 单位分
   * @return 单位元
   */
  public static BigDecimal toYun(BigDecimal number) {
    return toYun(number, 2);
  }

  /**
   * @param number 单位分
   * @param scale 小数位数
   * @return 单位元
   */
  public static BigDecimal toYun(BigDecimal number, int scale) {
    return number.divide(new BigDecimal(100), scale, BigDecimal.ROUND_HALF_UP);
  }
}
