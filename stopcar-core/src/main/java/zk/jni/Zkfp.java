package zk.jni;

/**
 * ERROR_DLOPEN 		-1001  内部显示连接库失败
 *
 * ERROR_DLSYM 		-1002  内部加载函数接口失败
 *
 * ERROR_BIOKEY_INIT 	-1003  初始化失败
 *
 * 比对成功返回值>0;其他表示失败
 */
public class Zkfp {

  public native static int SingleMatch(byte[] JpucTmp1, byte[] JpucTmp2);

  static {
    System.loadLibrary("fpmatch");
  }


}
