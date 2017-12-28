package com.park.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gif4j.GifDecoder;
import com.gif4j.GifEncoder;
import com.gif4j.GifImage;
import com.gif4j.GifTransformer;

/**
 * 从JDK1.7开始，中com.sun.image.codec.jpeg这个类被删除
 * 改用ImageWriter
 */
public class ImageUtil {
	static Logger log = LoggerFactory.getLogger(ImageUtil.class);

	/**
	 * 缩小jpg图片,先按照宽度比例缩小，然后按照高度缩小
	 *
	 * @param imgSrc  图片的源地址
	 * @param width   图片的最大允许的宽度
	 * @param height  图片的最大允许的高度
	 * @param imgDist 缩小后的图片存放路径
	 * @param type    "JPEG"或者"JPG","gif"
	 * @return false表示失败，true表示成功
	 */
	public static boolean minifyImage(File imgSrc, double width, double height, String imgDist, String type) {
		InputStream is = null;
		FileImageOutputStream os = null;
		try {
			double scale = 1;
			if (!imgSrc.exists()) {
				return false;
			}
			is = new FileInputStream(imgSrc);
			os = new FileImageOutputStream(new File(imgDist));
			// 获得图片
			Image src = javax.imageio.ImageIO.read(is);
			is.close();

			double img_width = src.getWidth(null);
			double img_height = src.getHeight(null);
			if (img_width > width) {
				// 图片的宽度大于给定要求的宽度，则先按照宽度缩放
				scale = img_width / width; // 得到缩小宽度比例
				img_width = width;// 缩小宽度
				img_height = img_height / scale;// 根据缩小的比例缩小高度
			}
			if (img_height > height) {
				// 若缩小后的高度仍然大于给定高度，再次缩小高度
				scale = img_height / height;// 缩小高度的比例
				img_height = height;// 缩小后的高度
				img_width = width / scale;// 再次缩小宽度
			}
			if ("gif".equalsIgnoreCase(type) && imgDist != null) {
				GifImage gifImage = GifDecoder.decode(imgSrc);// 创建一个GifImage对象.
				GifImage resizeIMG = GifTransformer.resize(gifImage, (int) img_width, (int) img_height, true);
				GifEncoder.encode(resizeIMG, new File(imgDist));
				return true;

			}
			BufferedImage bufferedImage = new BufferedImage((int) img_width, (int) img_height, BufferedImage.TYPE_INT_RGB);
			// 重构图片
			bufferedImage.getGraphics().drawImage(src.getScaledInstance((int) img_width, (int) img_height, Image.SCALE_SMOOTH), 0, 0, null);
			// 创建重构后的图片，然后保存到相应的地方

			ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // Needed see javadoc
			param.setCompressionQuality(0.9F); // Highest quality
			writer.setOutput(os);
			writer.write(bufferedImage);

			return true;
		} catch (Exception e) {
			log.error("上传图片失败" + e.getMessage(), e);
			return false;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.error("上传图片失败" + e.getMessage(), e);
				}
			}
			if (is != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.error("上传图片失败" + e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * 缩小jpg图片,先按照宽度比例缩小，然后按照高度缩小
	 *
	 * @param is      图片的源地址
	 * @param width   图片的最大允许的宽度
	 * @param height  图片的最大允许的高度
	 * @param imgDist 缩小后的图片存放路径
	 * @param type    "JPEG"或者"JPG","gif"
	 * @return false表示失败，true表示成功
	 */
	public static boolean minifyImage(InputStream is, double width, double height, String imgDist, String type) {
		FileImageOutputStream os = null;
		try {
			double scale = 1;
			if (is == null) {
				return false;
			}
			os = new FileImageOutputStream(new File(imgDist));
			// 获得图片
			Image src = javax.imageio.ImageIO.read(is);
			is.close();

			double img_width = src.getWidth(null);
			double img_height = src.getHeight(null);
			if (img_width > width) {
				// 图片的宽度大于给定要求的宽度，则先按照宽度缩放
				scale = img_width / width; // 得到缩小宽度比例
				img_width = width;// 缩小宽度
				img_height = img_height / scale;// 根据缩小的比例缩小高度
			}
			if (img_height > height) {
				// 若缩小后的高度仍然大于给定高度，再次缩小高度
				scale = img_height / height;// 缩小高度的比例
				img_height = height;// 缩小后的高度
				img_width = width / scale;// 再次缩小宽度
			}
			if ("gif".equalsIgnoreCase(type) && imgDist != null) {
				GifImage gifImage = GifDecoder.decode(is);// 创建一个GifImage对象.
				GifImage resizeIMG = GifTransformer.resize(gifImage, (int) img_width, (int) img_height, true);
				GifEncoder.encode(resizeIMG, new File(imgDist));
				return true;

			}
			BufferedImage bufferedImage = new BufferedImage((int) img_width, (int) img_height, BufferedImage.TYPE_INT_RGB);
			// 重构图片
			bufferedImage.getGraphics().drawImage(src.getScaledInstance((int) img_width, (int) img_height, Image.SCALE_SMOOTH), 0, 0, null);
			// 创建重构后的图片，然后保存到相应的地方
			ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // Needed see javadoc
			param.setCompressionQuality(0.9F); // Highest quality
			writer.setOutput(os);
			writer.write(bufferedImage);
			return true;
		} catch (Exception e) {
			log.error("上传图片失败" + e.getMessage(), e);
			return false;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.error("上传图片失败" + e.getMessage(), e);
				}
			}
			if (is != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.error("上传图片失败" + e.getMessage(), e);
				}
			}
		}
	}

}
