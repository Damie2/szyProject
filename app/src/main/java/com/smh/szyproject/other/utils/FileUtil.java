package com.smh.szyproject.other.utils;


import android.content.Context;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2015/12/31 0031.
 */
public class FileUtil {
    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static void deleteFile(String sPath) {
        Log.i("Xposed","要删除的文件： " + sPath);
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static void deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return;
        }
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                deleteFile(files[i].getAbsolutePath());
            } //删除子目录
            else {
                deleteDirectory(files[i].getAbsolutePath());
            }
        }
        //删除当前目录
        dirFile.delete();
    }
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0KB";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
    /**
     * 复制文件
     */
    public static boolean copyFile(InputStream in, OutputStream out) {
        try {
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = in.read(buffer)) > 0) {
                out.write(buffer, 0, i);
                out.flush();
            }
        } catch (Exception e) {
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                L.e(e);
            }
        }
        return true;
    }

    public static void mkDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void copyAssetsFile(Context context, String outDir, String assetsName) {
        try {
            InputStream open = context.getAssets().open(assetsName);
            File file = new File(outDir);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream out = new FileOutputStream(file);
            copyFile(open, out);
        } catch (IOException e) {
            L.e(e);
        }
    }

    public static boolean saveString(String str, String filePath) {

        if (isFileExist(filePath))
            new File(filePath).delete();

        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
            writer.write(str);
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            L.e(e);
            if (isFileExist(filePath))
                new File(filePath).delete();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String readString(String filePath) {
        if (!isFileExist(filePath)) {
            return null;
        }

        String content = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                content += line;
            }
        } catch (Exception e) {
            L.e(e);
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return content;
    }

    /**
     * 判断缓存是否存在
     *
     * @return
     */
    public static boolean isFileExist(String filePath) {
        File data = new File(filePath);
        return data.exists();
    }

    public static Object readObjectFromFile(String filePath) {

        if (!isFileExist(filePath))
            return null;

        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(filePath);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (Exception e) {
            L.e(e);
        } finally {
            try {
                if (fis != null)
                    fis.close();
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                L.e(e);
            }
        }
        return null;
    }

    public static boolean writeObjectToFile(Object object, String filePath) {

        if (isFileExist(filePath))
            new File(filePath).delete();

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(filePath);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.flush();
            return true;
        } catch (Exception e) {
            L.e(e);
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                L.e(e);
            }
        }
        return false;
    }

    public static String getFileBase64Str(File file) {
        try {
            InputStream in = new FileInputStream(file);
            byte[] data = new byte[in.available()];
            in.read(data);
            in.close();
            return new String(Base64.encodeToString(data, Base64.DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
