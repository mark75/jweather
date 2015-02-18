package com.prinzer.jweather;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.volley.toolbox.ImageLoader;

import android.os.Environment;
import android.provider.SyncStateContract.Constants;
import android.util.Log;

/**
 * 
 * @author vancy
 * 
 *         文件处理工具类
 * 
 */
public class FileUtils {

	public static final long B = 1;
	public static final long KB = B * 1024;
	public static final long MB = KB * 1024;
	public static final long GB = MB * 1024;
	public static final long BUFFER = 8129;

	/*
	 * 格式化文件的大小 带有单位
	 */
	public static String formatFileSize(long size) {
		StringBuilder sb = new StringBuilder();
		String u = null;
		double tmpSize = 0;
		if (size < KB) {
			sb.append(size).append("B");
		} else if (size < MB) {
			tmpSize = getSize(size, KB);
			u = "KB";
		} else if (size < GB) {
			tmpSize = getSize(size, MB);
			u = "MB";
		} else {
			tmpSize = getSize(size, GB);
			u = "GB";
		}
		return sb.append(twodot(tmpSize)).append(u).toString();
	}

	/**
	 * 保留两位小数
	 */
	public static String twodot(double d) {
		return String.format("%.2f", d);
	}

	public static double getSize(long size, long u) {
		return (double) size / (double) u;
	}

	/**
	 * 判断SD卡是否挂载且可用
	 */
	public static boolean isSdCardMounted() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 * 递归创建文件目录
	 */
	public static void CreateDir(String path) {
		if (!isSdCardMounted()) {
			return;
		}
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.mkdirs();
			} catch (Exception e) {
				Log.e("jbug", "error on create dirs:" + e.getStackTrace());
			}
		}
	}

	/**
	 * 读取文件
	 */
	public static String readTextFile(File file) {
		String text = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			text = readTextInputStream(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return text;
	}

	/**
	 * 从输入流中读取文件中的文本信息
	 */
	public static String readTextInputStream(InputStream is) {
		StringBuffer strBuffer = new StringBuffer();
		BufferedReader reader = null;
		String line;
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			while ((line = reader.readLine()) != null) {
				strBuffer.append(line).append("\r\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return strBuffer.toString();
	}

	/**
	 * 将文本内容写入到文件
	 */
	public static void writeTextFile(File file, String str) {
		DataOutputStream dout = null;
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			dout = new DataOutputStream(out);
			dout.write(str.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (dout != null) {
					dout.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * 获取一个文件夹的大小
	 */

	public static long getFileSize(File file) {
		long size = 0;
		File[] flist = file.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}

	/**
	 * 删除文件
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			file.delete();
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
			}
		}
		file.delete();
	}

}
