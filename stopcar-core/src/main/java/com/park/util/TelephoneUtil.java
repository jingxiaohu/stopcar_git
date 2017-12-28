package com.park.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelephoneUtil {

    /**
     * 判断是否是手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean checkIsUnion(String mobiles) {
        Pattern p = Pattern.compile("^((13[012])|(15[5,6])|(18[6]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 隐藏手机号，显示前三位和后四位，其余*
     *
     * @param str
     * @return
     */
    public static String hidePhone(String str) {
        if (null == str || "".equals(str)) {
            return null;
        }
        String regx = "(?<=\\d{3})\\d(?=\\d{4})";
        return str.replaceAll(regx, "*");
    }
}
