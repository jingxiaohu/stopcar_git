package com.park.util;

import com.park.bean.ReturnData;
import com.park.constants.AppProperties;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImageUploadUtil {

	/**
	 * 检查消息参数发送消息空检查
	 * 
	 * @param ui_id
	 *            用户编号
	 * @param content
	 *            消息内容
	 * @param flag
	 *            消息来源
	 * @return null能过验证，否则返回错误信息
	 */
	public static ReturnData checkMessageSendParam(long ui_id, String content, String flag) {

		ReturnData returnData = new ReturnData();
		if (RequestUtil.checkObjectBlank(ui_id)) {
			returnData.setReturnData("1", "0", "");
			return returnData;
		}
		if (RequestUtil.checkObjectBlank(flag)) {
			returnData.setReturnData("1", "1", "");
			return returnData;
		}
		if (RequestUtil.checkObjectBlank(content)) {
			returnData.setReturnData("1", "2", "");
			return returnData;
		}
		return null;
	}

	/**
	 * 评论消息参数检查
	 * 
	 * @param ui_id
	 * @param content
	 * @param flag
	 * @param mi_id
	 *            关联消息
	 * @return 返回null时通过验证
	 */
	public static ReturnData checkMessageCommentParam(long ui_id, String content, String flag, long mi_id) {

		ReturnData returnData = checkMessageSendParam(ui_id, content, flag);
		if (returnData == null) {
			if (RequestUtil.checkObjectBlank(mi_id)) {
				// 消息id空检查
				returnData = new ReturnData();
				returnData.setReturnData("1", "3", "");
				return returnData;
			}

		}
		return returnData;
	}


	/**
	 * 上传并缩小图片
	 * 
	 * @param image
	 *            图片文件
	 * @param imageFileName
	 *            图片文件的文件名
	 * @param maxWidth
	 *            最大高度
	 * @param maxHeight  
	 *            最大宽度   
	 * @return 图片文件的绝对url地址   
	 * Constants.AVATAR_FOLDER
	 */
	public static String uploadScaleImage(File image, String imageFileName, int maxWidth, int maxHeight,String realpath,String FILE_FOLDER) {
		// 得到文件的上传路径文件夹
		String targetImageFilePath = realpath;
		// 得到图片文件的类型
		int index = imageFileName.lastIndexOf(".");
		String imageType = imageFileName.substring(index + 1);
		// url相对地址
		String img_url = "";
		String imageAbsolutePath = targetImageFilePath + File.separator + imageFileName;
		// 最大高度与最大宽度同时为0时不缩放
		if (maxWidth == 0 && maxHeight == 0) {
			try {
				FileUtil.uploadFile(image, imageAbsolutePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			int url_index = imageAbsolutePath.indexOf(FILE_FOLDER);
			// url相对地址
			img_url = imageAbsolutePath.substring(url_index);
		} else {
			// 缩小并存放图片
			ImageUtil.minifyImage(image, maxWidth, maxHeight, imageAbsolutePath, imageType);
			// 构建文件访问的url地址
			int url_index = imageAbsolutePath.indexOf(FILE_FOLDER);
			// url相对地址
			img_url = imageAbsolutePath.substring(url_index);
		}
		return AppProperties.getBaseUrl() + img_url;
	}

	/**
	 * 上传语音并转化mp3文件,注此方法不完整
	 * 
	 * @param FILE_FOLDER ：文件存在文件夹名称
	 * @return 返回的是文件的绝对路径
	 * @throws IOException
	 */
	public static String uploadAndConvertVoice(File voice, String voiceFileName, String flag,String FILE_FOLDER,String realpath) {

		// 得到文件的上传路径文件夹
		//String targetImageFilePath = FileUtil.makeFolder(FileUtil.makeOccasionallyDir(Constants.BASE_DIR, FILE_FOLDER));
		String targetImageFilePath = realpath;
		// 定义最终写入的绝对路径
		String targetFilePath = "";
		String sttemp = voiceFileName.toLowerCase();
		int index = sttemp.indexOf(".");
		if (index > 0) {
			String File_ZMX = sttemp.substring(index);// 文件名后缀
			targetFilePath = targetImageFilePath + File.separator+ RequestUtil.getUUID() + File_ZMX;
		} else {
			targetFilePath = targetImageFilePath + File.separator+ RequestUtil.getUUID() + ".mp3";
		}
		
		// 上传文件
		boolean returnflag = false;
		try {
			returnflag = FileUtil.uploadFile(voice, targetFilePath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(returnflag){//上传成功
			try {
				if(!targetFilePath.endsWith(".mp3")){
					String temp = targetFilePath.replace(".amr", ".mp3");
					if(targetFilePath.endsWith(".m4a"))
						temp = targetFilePath.replace(".m4a", ".mp3");
					 BufferedReader reader = null;
			         try {
			        	 Process r = null;
			        	 if(targetFilePath.endsWith(".pcm")){
			        		 temp = targetFilePath.replace(".pcm", ".mp3");
			        		 r = Runtime.getRuntime().exec("ffmpeg -f s16le -ar 11025 -ac 2 -i "+targetFilePath+" "+temp);
			        	 }
			        	 else
			        		 r = Runtime.getRuntime().exec("ffmpeg -i "+targetFilePath+" -f mp3 -ac 2 -ab 32 -vn "+temp);
			        	 StringBuffer sb = new StringBuffer();
			        	 String line=null;
			        	 reader = new BufferedReader(new InputStreamReader(r.getErrorStream()));
			             while((line=reader.readLine())!=null){
			                 sb.append(line);
			             }
			             if(sb.toString().indexOf("muxing overhead")!=-1){
			            	 File f = new File(targetFilePath);
			            	 f.delete();
			             }else{
			            	 return null;//转换错误
			             }
			         } catch (IOException e) {
			        	 e.printStackTrace();
			        	 return null;//转换错误
			         }finally{
			        	 if(reader!=null)
			        		 reader.close();
			         }
					targetFilePath = temp;
				}
			} catch (IOException e) {
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
}
