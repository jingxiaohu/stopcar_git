package com.park;

import java.io.File;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * @author Peter Wu
 */
public class CodeCount {

  @Test
  public void comCode() throws Exception {
    String absolutePath = new File("").getAbsolutePath();
    Collection<File> files = FileUtils.listFiles(new File(absolutePath).getParentFile(), new String[]{"java"}, true);
    int count=0;
    File comcode=new File("target/code.txt");
    comcode.delete();
    for (File file : files) {
      List<String> lines = FileUtils.readLines(file, "UTF-8");
      count+= lines.size();
      FileUtils.writeLines(comcode,lines,true);
    }
    System.err.println(count);//110290 62434
    System.err.println(comcode);
  }
}
