package com.park.jni;

import java.util.List;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Base64Utils;
import zk.jni.Zkfv;

/**
 * @author Peter Wu
 */
class ZkfvTest {

  public static void main(String[] args) throws Exception {
    List<String> fv1 = FileUtils.readLines(new ClassPathResource("fv/1.txt").getFile(), "UTF-8");
    List<String> fv2 = FileUtils.readLines(new ClassPathResource("fv/2.txt").getFile(), "UTF-8");
    for (String s : fv1) {
      for (String s1 : fv2) {
        for (String s2 : s1.split(",")) {
          for (String s3 : s.split(",")) {
            System.err.println(Zkfv.SingleMatch(Base64Utils.decodeFromString(s2), Base64Utils.decodeFromString(s3)));
          }
        }
      }
    }
    System.err.println(Zkfv.SingleMatch(Base64Utils.decodeFromString(fv2.get(0).split(",")[0]), Base64Utils.decodeFromString(fv2.get(0).split(",")[1])));
    System.err.println(Zkfv.SingleMatch(Base64Utils.decodeFromString(fv2.get(1).split(",")[0]), Base64Utils.decodeFromString(fv2.get(1).split(",")[1])));
    System.err.println(Zkfv.SingleMatch(Base64Utils.decodeFromString(fv2.get(2).split(",")[0]), Base64Utils.decodeFromString(fv2.get(2).split(",")[1])));

  }
}
