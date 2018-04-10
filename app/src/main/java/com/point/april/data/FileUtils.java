package com.point.april.data;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.point.april.global.GlobalConstant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

	private static final String TAG = FileUtils.class.getSimpleName();
	
	public static void saveBitmap(Bitmap bm, String picName) {
		Log.d(TAG, "保存图片");
		try {
			if (!isFileExist("")) {
				createSDDir("");
			}
			File f = new File(GlobalConstant.FORMATS_PATH, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			Log.d(TAG, "已经保存");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getFile(String fileName) throws IOException {
		File file = new File(GlobalConstant.FORMATS_PATH + fileName);
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			System.out.println("create File :" + file.getAbsolutePath());
			boolean boo = file.createNewFile();
			System.out.println("create File :" + boo);
		}
		return file;
	}

	public static File getFilePath(String filePath, String fileName) {
		File file = null;
		filePath = GlobalConstant.FORMATS_PATH+filePath;
		makeRootDirectory(filePath);
		try {
			file = new File(filePath + fileName+".txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public static void makeRootDirectory(String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(GlobalConstant.FORMATS_PATH + dirName);
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(GlobalConstant.FORMATS_PATH + fileName);
		file.isFile();
		return file.exists();
	}

	public static void delFile(String fileName) {
		File file = new File(GlobalConstant.FORMATS_PATH + fileName);
		if (file.isFile()) {
			file.delete();
		}
		file.exists();
	}

	public static void deleteDir() {
		File dir = new File(GlobalConstant.FORMATS_PATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); // 删除所有文件
			else if (file.isDirectory())
				deleteDir(); // 递规的方式删除文件夹
		}
		dir.delete();// 删除目录本身
	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}

	public String getString(String s) {
		String path = null;
		if (s == null)
			return "";
		for (int i = s.length() - 1; i > 0; i++) {
			s.charAt(i);
		}
		return path;
	}
}
