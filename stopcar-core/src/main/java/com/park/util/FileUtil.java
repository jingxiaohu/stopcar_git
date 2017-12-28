package com.park.util;

import com.park.constants.AppProperties;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

  private static Logger log = LoggerFactory.getLogger(FileUtil.class);

  /**
   * 根据相对地址生成文件存放的绝对地址
   *
   * @return 应该存储文件绝对地址
   */
  public static String makeFolder(String srcPath) {
    File filePath = new File(srcPath);
    // 检查文件夹是否存在，不存在新建文件夹
    if (!filePath.exists()) {
      filePath.mkdirs();
    }
    // 取得对应目录下的所有文件
    File[] folderFiles = filePath.listFiles();

    // 取得src文件夹下面的文件个数
    int fileCount = folderFiles.length;
    if (fileCount != 0) {
      // 文件夹从0开始
      fileCount = folderFiles.length - 1;
    }

    // 检测最后一个文件夹存在与否
    File lastFileFloder = new File(srcPath + File.separator + fileCount);
    if (!lastFileFloder.exists()) {
      // 如果不存在，新建文件夹
      lastFileFloder.mkdirs();
      // 返回文件夹的绝对路径
      return lastFileFloder.getAbsolutePath();
    } else {
      // 已经存在，检查这个文件夹的文件数目是否大于30
      int lastFoldercount = lastFileFloder.listFiles().length;
      if (lastFoldercount >= 30) {
        fileCount++;
        lastFileFloder = new File(srcPath + File.separator + fileCount);
        // 新建一个文件夹
        lastFileFloder.mkdirs();
      }

    }

    return lastFileFloder.getAbsolutePath();

  }

  /**
   * 下载文件到本地
   * @param url
   * @return
   */
/*	public static String getUrlFile(String url){
		try{
			String pix ="";
			if(url.toLowerCase().endsWith("png")){ 
				pix = ".png";
			}else if(url.toLowerCase().endsWith("gif")){ 
				pix = ".gif";
			}else pix=".jpg";
			URL u = new URL(url);
			URLConnection con = u.openConnection();
			String path = FileUtil.makeFolder(FileUtil.makeAvatorDir(AppProperties.getBaseDir()))+"/"+System.currentTimeMillis()+pix;
			File file = new File(path);
			byte b[] = new byte[1024];
			BufferedInputStream in = new BufferedInputStream(con.getInputStream());
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			int s = 0;
			while((s = in.read(b))!=-1){
				out.write(b, 0, s);
			}
			in.close();
			out.close();
			int url_index = path.indexOf(Constants.AVATAR_FOLDER);
			// url相对地址
			String img_url = path.substring(url_index);
			// 替换/为\ (注windows下)
//			 img_url = FileUtil.replaceSlash(img_url);
			return Constants.AVATAR_BASE_URL + img_url;
		}catch (Exception e) {
			log.error("getUrlFile error", e);
			return "";
		}
	}
*/
  /**
   * 根据年份创建channel文件的文件夹
   *
   * @param baseDir
   *            文件夹的根目录
   * @return channel图片文件的根目录
   */
/*	public static String makeAvatorDir(String baseDir) {
		Calendar calendar = Calendar.getInstance();
		// 得到今年的年份
		int year = calendar.get(Calendar.YEAR);
		// 根据年份创建channel文件的文件夹
		File channelImgDir = new File(baseDir + File.separator + Constants.AVATAR_FOLDER + File.separator + year);
		if (!channelImgDir.exists()) {
			channelImgDir.mkdirs();
		}

		return channelImgDir.getAbsolutePath();
	}*/

  /**
   * 替换\为/
   */
  public static String replaceSlash(String path) {

    Pattern pattern = Pattern.compile("\\\\");
    String convert_path = "";
    Matcher matcher = pattern.matcher(path);
    if (matcher.find()) {
      convert_path = path.replaceAll("\\\\", "/");
    }

    return convert_path;

  }

  /**
   * 删除头像原来的图片
   *
   * @param imageURl
   *            数据库中图片的imageURl对应
   */
/*	public static void deleteAvatorByURl(String imageurlPath) {
		int index = imageurlPath.indexOf(Constants.AVATAR_FOLDER);
		// 拼接得到文件的绝对地址
		String filePath = AppProperties.getBaseDir() + File.separator + imageurlPath.substring(index);
		File imagefile = new File(filePath);
		if (imagefile.exists()) {
			// 如果图片存在就删除图片
			imagefile.delete();
		}

	}*/

  /**
   * 将文件写入到指定路径
   *
   * @param srcFile 源文件
   * @param targetFilepath 目录文件的绝对地址
   */
  public static boolean uploadFile(File srcFile, String targetFilepath) throws IOException {
    FileInputStream fins = null;
    FileOutputStream fos = null;
    try {
      fins = new FileInputStream(srcFile);
      fos = new FileOutputStream(targetFilepath);
      byte[] buffer = new byte[1024 * 1024];
      int length = 0;
      while ((length = fins.read(buffer)) != -1) {
        fos.write(buffer, 0, length);
      }
      return true;

    } catch (Exception e) {
      log.error("uploadFile error", e);
      return false;
    } finally {
      if (fins != null) {
        try {
          fins.close();
        } catch (IOException e) {
          log.error("uploadFile error", e);
        }
      }
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          log.error("uploadFile error", e);
        }
      }
    }

  }


  /**
   * 将文件写入到指定路径
   *
   * @param targetFilepath 目录文件的绝对地址
   */
  public static boolean uploadFile(InputStream fins, String targetFilepath) throws IOException {
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(targetFilepath);
      byte[] buffer = new byte[1024 * 1024];
      int length = 0;
      while ((length = fins.read(buffer)) != -1) {
        fos.write(buffer, 0, length);
      }
      return true;

    } catch (Exception e) {
      log.error("uploadFile error", e);
      return false;
    } finally {
      if (fins != null) {
        try {
          fins.close();
        } catch (IOException e) {
          log.error("uploadFile error", e);
        }
      }
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          log.error("uploadFile error", e);
        }
      }
    }

  }


  /**
   * 根据年份创建声音存放文件的文件夹
   *
   * @param baseDir 文件夹的根目录
   * @param file_folder 文件夹的目录
   * @return 文件绝对路径
   */
  public static String makeOccasionallyDir(String baseDir, String file_folder) {
    Calendar calendar = Calendar.getInstance();
    // 得到今年的年份
    int year = calendar.get(Calendar.YEAR);
    // 根据年份创建channel文件的文件夹
    File OCcasionallyDir = new File(baseDir + File.separator + file_folder + File.separator + year);
    if (!OCcasionallyDir.exists()) {
      OCcasionallyDir.mkdirs();
    }

    return OCcasionallyDir.getAbsolutePath();
  }

  /**
   * 根据年份创建声音存放文件的文件夹
   *
   * @return 文件绝对路径
   */
  public static String makeOccasionallyDir(String file_path) {
    Calendar calendar = Calendar.getInstance();
    // 得到今年的年份
    int year = calendar.get(Calendar.YEAR);
    // 根据年份创建channel文件的文件夹
    File OCcasionallyDir = new File(file_path + File.separator + year);
    if (!OCcasionallyDir.exists()) {
      OCcasionallyDir.mkdirs();
    }
    return OCcasionallyDir.getAbsolutePath();
  }

  /**
   * 上传并缩小图片
   *
   * @param image 图片文件
   * @param imageFileName 图片文件的文件名
   * @param file_folder 文件夹的目录
   * @param maxWidth 最大高度
   * @param maxHeight 最大宽度
   * @return 图片文件的绝对url地址
   */
  public static String uploadScaleImage(File image, String imageFileName, String file_folder,
      int maxWidth, int maxHeight, String telephone) throws IOException {
    // 得到文件的上传路径文件夹
    String targetImageFilePath = FileUtil
        .makeFolder(FileUtil.makeOccasionallyDir(AppProperties.getBaseDir(), file_folder));
    // 得到图片文件的类型
    int index = imageFileName.lastIndexOf(".");
    String imageType = imageFileName.substring(index + 1);
    // 得到文件名
    String imageName = "";
    if (telephone == null) {
      imageName = RequestUtil.getUUID() + "." + imageType;
    } else {
      imageName =
          "head" + telephone + "_" + RandomStringUtils.random(5, false, true) + "." + imageType;
    }
    // url相对地址
    String img_url = "";
    String imageAbsolutePath = targetImageFilePath + File.separator + imageName;
    // 最大高度与最大宽度同时为0时不缩放
    if (maxWidth == 0 && maxHeight == 0) {
      FileUtil.uploadFile(image, imageAbsolutePath);
      int url_index = imageAbsolutePath.indexOf(file_folder);
      // url相对地址
      img_url = imageAbsolutePath.substring(url_index);
    } else {
      // 缩小并存放图片
      boolean bool = ImageUtil
          .minifyImage(image, maxWidth, maxHeight, imageAbsolutePath, imageType);
      if (!bool) {
        return null;
      }
      // 构建文件访问的url地址
      int url_index = imageAbsolutePath.indexOf(file_folder);
      // url相对地址
      img_url = imageAbsolutePath.substring(url_index);
      // 需要替换GIF为JPG
			/*if(bool){
				img_url = img_url.replace("."+imageType, ".jpg");
			}*/

    }
    if (RequestUtil.checkObjectBlank(img_url)) {
      return null;
    }
    return AppProperties.getBaseUrl() + img_url;
  }


  /**
   * 上传并缩小图片
   *
   * @param image 图片文件
   * @param imageFileName 图片文件的文件名
   * @param file_folder 文件夹的目录
   * @param maxWidth 最大高度
   * @param maxHeight 最大宽度
   * @return 图片文件的绝对url地址
   */
  public static String uploadScaleAvatarImage(File image, String imageFileName, String file_folder,
      int maxWidth, int maxHeight, String telephone) throws IOException {
    // 得到文件的上传路径文件夹
    String targetImageFilePath = FileUtil
        .makeOccasionallyDir(AppProperties.getBaseDir(), file_folder);
    // 得到图片文件的类型
    int index = imageFileName.lastIndexOf(".");
    String imageType = imageFileName.substring(index + 1);
    // 得到文件名
    String imageName = "";
    if (telephone == null) {
      imageName = RequestUtil.getUUID() + "." + imageType;
    } else {
      imageName =
          "head" + telephone + "_" + RandomStringUtils.random(5, false, true) + "." + imageType;
    }
    // url相对地址
    String img_url = "";
    String imageAbsolutePath = targetImageFilePath + File.separator + imageName;
    // 最大高度与最大宽度同时为0时不缩放
    if (maxWidth == 0 && maxHeight == 0) {
      FileUtil.uploadFile(image, imageAbsolutePath);
      int url_index = imageAbsolutePath.indexOf(file_folder);
      // url相对地址
      img_url = imageAbsolutePath.substring(url_index);
    } else {
      // 缩小并存放图片
      boolean bool = ImageUtil
          .minifyImage(image, maxWidth, maxHeight, imageAbsolutePath, imageType);
      if (!bool) {
        return null;
      }
      //替换WINDOWS的文件分隔符
      imageAbsolutePath = replaceWindowsFileSeparator(imageAbsolutePath);
      // 构建文件访问的url地址
      int url_index = imageAbsolutePath.indexOf(file_folder);
      // url相对地址
      img_url = imageAbsolutePath.substring(url_index);
      // 需要替换GIF为JPG
			/*if(bool){
				img_url = img_url.replace("."+imageType, ".jpg");
			}*/

    }
    if (RequestUtil.checkObjectBlank(img_url)) {
      return null;
    }
    return AppProperties.getBaseUrl() + img_url;
  }


  /**
   * 上传并缩小图片
   *
   * @param image 图片文件
   * @param imageFileName 图片文件的文件名
   * @param file_folder 文件夹的目录
   * @param maxWidth 最大高度
   * @param maxHeight 最大宽度
   * @return 图片文件的绝对url地址
   */
  public static String uploadScaleAvatarImage(MultipartFile image, String imageFileName,
      String file_folder, int maxWidth, int maxHeight, String telephone) throws IOException {

    // 得到文件的上传路径文件夹
    String targetImageFilePath = FileUtil
        .makeOccasionallyDir(AppProperties.getBaseDir(), file_folder);
    // 得到图片文件的类型
    int index = imageFileName.lastIndexOf(".");
    String imageType = imageFileName.substring(index + 1);
    // 得到文件名
    String imageName = "";
    if (telephone == null) {
      imageName = RequestUtil.getUUID() + "." + imageType;
    } else {
      imageName =
          "head" + telephone + "_" + RandomStringUtils.random(5, false, true) + "." + imageType;
    }
    // url相对地址
    String img_url = "";
    String imageAbsolutePath = targetImageFilePath + File.separator + imageName;
    // 最大高度与最大宽度同时为0时不缩放
    if (maxWidth == 0 && maxHeight == 0) {
      FileUtil.uploadFile(image.getInputStream(), imageAbsolutePath);
      int url_index = imageAbsolutePath.indexOf(file_folder);
      // url相对地址
      img_url = imageAbsolutePath.substring(url_index);
    } else {
      // 缩小并存放图片
      boolean bool = ImageUtil
          .minifyImage(image.getInputStream(), maxWidth, maxHeight, imageAbsolutePath, imageType);
      log.info("图片名及后缀，ole:{},new:{}", imageFileName, imageType);
      if (!bool) {
        return null;
      }
      //替换WINDOWS的文件分隔符
      imageAbsolutePath = replaceWindowsFileSeparator(imageAbsolutePath);
      // 构建文件访问的url地址
      int url_index = imageAbsolutePath.indexOf(file_folder);
      // url相对地址
      img_url = imageAbsolutePath.substring(url_index);
      // 需要替换GIF为JPG
			/*if(bool){
				img_url = img_url.replace("."+imageType, ".jpg");
			}*/

    }
    if (RequestUtil.checkObjectBlank(img_url)) {
      return null;
    }
    return AppProperties.getBaseUrl() + img_url;
  }

  /**
   * 上传并缩小图片
   *
   * @param image 图片文件
   * @param imageFileName 图片文件的文件名
   * @param file_folder 文件夹的目录
   * @param maxWidth 最大高度
   * @param maxHeight 最大宽度
   * @return 图片文件的绝对url地址
   */
  public static String uploadScaleImage(MultipartFile image, String imageFileName,
      String file_folder, int maxWidth, int maxHeight, String pre) throws IOException {
    // 得到文件的上传路径文件夹
    String targetImageFilePath = FileUtil
        .makeOccasionallyDir(AppProperties.getBaseDir(), file_folder);
    // 得到图片文件的类型
    int index = imageFileName.lastIndexOf(".");
    String imageType = imageFileName.substring(index + 1);
    // 得到文件名
    String imageName = "";
    if (pre == null) {
      imageName = RequestUtil.getUUID() + "." + imageType;
    } else {
      imageName = "head" + pre + "_" + RandomStringUtils.random(5, false, true) + "." + imageType;
    }
    // url相对地址
    String img_url = "";
    String imageAbsolutePath = targetImageFilePath + File.separator + imageName;
    // 最大高度与最大宽度同时为0时不缩放
    if (maxWidth == 0 && maxHeight == 0) {
      FileUtil.uploadFile(image.getInputStream(), imageAbsolutePath);
      int url_index = imageAbsolutePath.indexOf(file_folder);
      // url相对地址
      img_url = imageAbsolutePath.substring(url_index);
    } else {
      // 缩小并存放图片
      boolean bool = ImageUtil
          .minifyImage(image.getInputStream(), maxWidth, maxHeight, imageAbsolutePath, imageType);
      if (!bool) {
        return null;
      }
      //替换WINDOWS的文件分隔符
      imageAbsolutePath = replaceWindowsFileSeparator(imageAbsolutePath);
      // 构建文件访问的url地址
      int url_index = imageAbsolutePath.indexOf(file_folder);
      // url相对地址
      img_url = imageAbsolutePath.substring(url_index);
      // 需要替换GIF为JPG
			/*if(bool){
				img_url = img_url.replace("."+imageType, ".jpg");
			}*/

    }
    if (RequestUtil.checkObjectBlank(img_url)) {
      return null;
    }
    return AppProperties.getBaseUrl() + img_url;
  }


  /**
   * 上传语音并转化mp3文件,注此方法不完整
   *
   * @param voice :音频文件
   * @return 返回的是文件的绝对路径
   */
  public static String uploadAndConvertVoice(File voice, String voiceFileName, String flag,
      String FILE_FOLDER) throws IOException {

    // 得到文件的上传路径文件夹
    String targetImageFilePath = FileUtil
        .makeFolder(FileUtil.makeOccasionallyDir(AppProperties.getBaseDir(), FILE_FOLDER));
    // 定义最终写入的绝对路径
    String targetFilePath = "";
    String sttemp = voiceFileName.toLowerCase();
    int index = sttemp.indexOf(".");
    if (index > 0) {
      String File_ZMX = sttemp.substring(index);// 文件名后缀
      targetFilePath = targetImageFilePath + File.separator + RequestUtil.getUUID() + File_ZMX;
    } else {
      targetFilePath = targetImageFilePath + File.separator + RequestUtil.getUUID() + ".mp3";
    }
    // 上传文件
    boolean returnflag = FileUtil.uploadFile(voice, targetFilePath);
    if (returnflag) {//上传成功
      try {
        if (!targetFilePath.endsWith(".mp3")) {
          String temp = targetFilePath.replace(".amr", ".mp3");
          if (targetFilePath.endsWith(".m4a")) {
            temp = targetFilePath.replace(".m4a", ".mp3");
          }
          BufferedReader reader = null;
          try {
            Process r = null;
            if (targetFilePath.endsWith(".pcm")) {
              temp = targetFilePath.replace(".pcm", ".mp3");
              r = Runtime.getRuntime()
                  .exec("ffmpeg -f s16le -ar 11025 -ac 2 -i " + targetFilePath + " " + temp);
            } else {
              r = Runtime.getRuntime()
                  .exec("ffmpeg -i " + targetFilePath + " -f mp3 -ac 2 -ab 32 -vn " + temp);
            }
            StringBuffer sb = new StringBuffer();
            String line = null;
            reader = new BufferedReader(new InputStreamReader(r.getErrorStream()));
            while ((line = reader.readLine()) != null) {
              sb.append(line);
            }
            if (sb.toString().indexOf("muxing overhead") != -1) {
              File f = new File(targetFilePath);
              f.delete();
            } else {
              return null;//转换错误
            }
          } catch (IOException e) {
            log.error("uploadAndConvertVoice error", e);
            return null;//转换错误
          } finally {
            if (reader != null) {
              reader.close();
            }
          }
          targetFilePath = temp;
        }
      } catch (IOException e) {
        log.error("uploadAndConvertVoice error", e);
        return null;
      }
      if (targetFilePath == null || "".equals(targetFilePath)) {
        // 无效
        return null;
      }
      // 构建文件访问的url地址
      int url_index = targetFilePath.indexOf(FILE_FOLDER);
      // url相对地址
      String voice_url = targetFilePath.substring(url_index);
      return AppProperties.getBaseUrl() + voice_url;
    }
    return null;
  }

  /**
   * 替换Windows中的文件分隔符
   */
  public static String replaceWindowsFileSeparator(String str) {
    if ("\\".equalsIgnoreCase(File.separator)) {
      return str.replace("\\", "/");
    }
    return str;
  }
}
