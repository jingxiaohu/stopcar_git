package com.park.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class HttpPostUtil {
	URL url;
	HttpURLConnection conn;
	String boundary = "--------httppost123";
	Map<String, String> textParams = new HashMap<String, String>();
	Map<String, File> fileparams = new HashMap<String, File>();
	DataOutputStream ds;

	public HttpPostUtil(String url) throws Exception {
		this.url = new URL(url);
	}

	// ��������Ҫ����ķ�������ַ�����ϴ��ļ��ĵ�ַ��
	public void setUrl(String url) throws Exception {
		this.url = new URL(url);
	}

	// ����һ����ͨ�ַ���ݵ�form�?�����
	public void addTextParameter(String name, String value) {
		textParams.put(name, value);
	}

	// ����һ���ļ���form�?�����
	public void addFileParameter(String name, File value) {
		fileparams.put(name, value);
	}

	// �����������ӵ�form�?���
	public void clearAllParameters() {
		textParams.clear();
		fileparams.clear();
	}

	// ������ݵ�������������һ���ֽڰ�������ķ��ؽ�������
	public byte[] send() throws Exception {
		initConnection();
		try {
			conn.connect();
		} catch (SocketTimeoutException e) {
			// something
			throw new RuntimeException();
		}
		ds = new DataOutputStream(conn.getOutputStream());
		writeFileParams();
		writeStringParams();
		paramsEnd();
		InputStream in = conn.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		conn.disconnect();
		return out.toByteArray();
	}

	// �ļ��ϴ���connection��һЩ��������
	private void initConnection() throws Exception {
		conn = (HttpURLConnection) this.url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setConnectTimeout(10000); // ���ӳ�ʱΪ10��
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
	}

	// ��ͨ�ַ����
	private void writeStringParams() throws Exception {
		Set<String> keySet = textParams.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String name = it.next();
			String value = textParams.get(name);
			ds.writeBytes("--" + boundary + "\r\n");
			ds.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"\r\n");
			ds.writeBytes("\r\n");
			ds.writeBytes(encode(value) + "\r\n");
		}
	}

	// �ļ����
	private void writeFileParams() throws Exception {
		Set<String> keySet = fileparams.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String name = it.next();
			File value = fileparams.get(name);
			ds.writeBytes("--" + boundary + "\r\n");
			ds.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + encode(value.getName()) + "\"\r\n");
			ds.writeBytes("Content-Type: " + getContentType(value) + "\r\n");
			ds.writeBytes("\r\n");
			ds.write(getBytes(value));
			ds.writeBytes("\r\n");
		}
	}

	// ��ȡ�ļ����ϴ����ͣ�ͼƬ��ʽΪimage/png,image/jpg�ȡ���ͼƬΪapplication/octet-stream
	private String getContentType(File f) throws Exception {

		// return "application/octet-stream"; //
		// ���в���ϸ���Ƿ�ΪͼƬ��ȫ����Ϊapplication/octet-stream ����
		ImageInputStream imagein = ImageIO.createImageInputStream(f);
		if (imagein == null) {
			return "application/octet-stream";
		}
		Iterator<ImageReader> it = ImageIO.getImageReaders(imagein);
		if (!it.hasNext()) {
			imagein.close();
			return "application/octet-stream";
		}
		imagein.close();
		return "image/" + it.next().getFormatName().toLowerCase();// ��FormatName���ص�ֵת����Сд��Ĭ��Ϊ��д

	}

	// ���ļ�ת�����ֽ�����
	private byte[] getBytes(File f) throws Exception {
		FileInputStream in = new FileInputStream(f);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int n;
		while ((n = in.read(b)) != -1) {
			out.write(b, 0, n);
		}
		in.close();
		return out.toByteArray();
	}

	// ��ӽ�β���
	private void paramsEnd() throws Exception {
		ds.writeBytes("--" + boundary + "--" + "\r\n");
		ds.writeBytes("\r\n");
	}

	// �԰����ĵ��ַ����ת�룬��ΪUTF-8���������Ǳ�Ҫ����һ�ν���
	private String encode(String value) throws Exception {
		return URLEncoder.encode(value, "UTF-8");
	}

//	public static void main(String[] args) throws Exception {
//		HttpPostUtil u = new HttpPostUtil("http://192.168.0.202:8094/channel.do");
//		u.addTextParameter("ui_id", "e1cabec8f0514f9fb112227e4f21ffef");
//		u.addFileParameter("image", new File("c:\\1.jpg"));
//		u.addTextParameter("name", "this is my channel");
//		u.addTextParameter("flag", "2");
//		byte[] b = u.send();
//		String result = new String(b);
//		System.out.println(result);
//	}

}
