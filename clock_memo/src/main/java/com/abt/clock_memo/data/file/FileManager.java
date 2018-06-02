package com.abt.clock_memo.data.file;

import android.content.Context;
import android.util.Log;

import com.abt.clock_memo.bean.SignIn;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String TAG = FileManager.class.getSimpleName();
    /**
     * 把list数据写入到文件
     * @param context
     * @param fileName
     * @param list
     */
    public static void write(Context context, String fileName, List<SignIn> list) {
        ObjectOutputStream objOutStream = null;
        FileOutputStream fileOutStream = null;
        try {
            fileOutStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            objOutStream = new ObjectOutputStream(fileOutStream);
            objOutStream.writeObject(list);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

        if (objOutStream != null) {
            try {
                objOutStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (fileOutStream != null) {
            try {
                fileOutStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * It store the file data into the file given by the filename
     *
     * @param context  the activity context
     * @param fileName the filename to store in
     * @param fileData the string to be stored
     */
    public static void writeStr(Context context, String fileName, String fileData) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(fileData.getBytes());
            fos.close();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    /**
     * 把list数据从文件中读出来
     * @param context
     * @param fileName
     * @return
     */
    public static ArrayList<SignIn> read(Context context, String fileName) {
        ObjectInputStream objInStream = null;
        FileInputStream fileInStream = null;
        ArrayList<SignIn> savedArrayList = new ArrayList<SignIn>();
        try {
            fileInStream = context.openFileInput(fileName);
            objInStream = new ObjectInputStream(fileInStream);
            savedArrayList = (ArrayList<SignIn>) objInStream.readObject();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return savedArrayList;
    }

    /**
     * Reads the data stored in the file given by the filename
     *
     * @param context  the activity context
     * @param fileName the name of the filename
     * @return the string read
     */
    public static String readStr(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            StringBuilder builder = new StringBuilder();
            int ch;
            while ((ch = fis.read()) != -1) {
                builder.append((char) ch);
            }
            return builder.toString();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return "";
    }

}
