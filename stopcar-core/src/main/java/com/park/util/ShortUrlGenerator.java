package com.park.util;
//package com.intimes.util;
//
//import java.util.Date;
//
//import com.intimes.bean.Short_url_info;
//import com.intimes.core.Constants;
//
///**
// * 生成端连接的加密地址
// * 
// * @author Administrator
// *
// */
//public class ShortUrlGenerator {
//
//    /**
//     * 生成短地址KEY
//     * @param url
//     * @return
//     */
//    public static String shortUrl(String url) {
//        String key = "2348(herf89fjodsf09" ;
//        String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
//               "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
//               "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
//               "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
//               "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
//               "U" , "V" , "W" , "X" , "Y" , "Z"
//        };
//        String sMD5EncryptResult = RequestUtil.MD5(key + url);
//        String hex = sMD5EncryptResult;
//
//        String sTempSubString = hex.substring(0 * 8, 0 * 8 + 8);
//        long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
//        String outChars = "" ;
//        for ( int j = 0; j < 6; j++) {
//           long index = 0x0000003D & lHexLong;
//           outChars += chars[( int ) index];
//           lHexLong = lHexLong >> 5;
//        }
//        Short_url_info s = new Short_url_info();
//        s.setCreatetime(new Date().getTime());
//        s.setId(outChars);
//        s.setVisits(0);
//        s.setSrc(url);
//        //int index_key = Constants.daoFactory.getShort_url_infoDao().insert(s);
//        return url;
//     }
//    
//}