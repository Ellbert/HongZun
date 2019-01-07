package com.cecilia.framework.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author stone
 */

public class FileUtil {

    private static final String FOLDER_NAME = "GuGuang";

    private FileUtil() {
        throw new AssertionError();
    }

    /**
     * 将保存的文件取出后，转换成对象返回
     */
    public static Object loadBaseObject(String str) {
        ObjectInputStream objectIn = null;
        Object object = null;
        try {
            FileInputStream fileIn = ViewUtil.getContext().openFileInput(str);
            objectIn = new ObjectInputStream(fileIn);
            object = objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

    /**
     * 将对象保存成文件
     */
    public static void saveBaseObject(Object object, String str) {
        ObjectOutputStream objectOut = null;
        try {
            FileOutputStream fileOut = ViewUtil.getContext().openFileOutput(str, 0);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            fileOut.getFD().sync();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取指定文件夹的大小
     */
    public static long getFolderSize(File dir) {
        long size = 0;
        File[] fileList = dir.listFiles();
        for (File file : fileList) {
            size += file.isDirectory() ? getFolderSize(file) : file.length();
        }
        return size;
    }

    /**
     * 获取指定文件夹里全部文件名
     */
    public static List<String> getFolderName(File dir) {
        List<String> name = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                name.addAll(getFolderName(file));
            } else {
                name.add(file.getName());
            }
        }
        return name;
    }

    /**
     * 删除指定文件夹
     */
    public static boolean deleteFolder(File dir) {
        if (dir == null || !dir.exists() || dir.isFile()) {
            return false;
        }
        for (File file : dir.listFiles()) {
            if (file.isFile() && !file.delete()) {
                LogUtil.e("deleteFolder " + file.getName() + " error");
            } else if (file.isDirectory()) {
                deleteFolder(file); // 递归
            }
        }
        if (!dir.delete()) {
            LogUtil.e("deleteFolder " + dir.getName() + " error");
            return false;
        }
        return true;
    }

    /**
     * 删除指定文件，并通知系统，适用于照片视频<br/>
     * 这样可以避免删除之后依旧可以在手机自带图库中查看到它
     */
    public static void deleteFile(Context context, File file) {
        if (file.exists() && file.delete()) {
            Intent media = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file));
            context.sendBroadcast(media);
        }
    }

    /**
     * 创建指定后缀名的文件
     *
     * @param suffix 后缀名
     */
    public static File createFile(String suffix) {
        File storageDir = new File(Environment.getExternalStorageDirectory(), FOLDER_NAME);
        if (!storageDir.exists() && !storageDir.mkdir()) {
            return null;
        }
        try {
            return File.createTempFile(generateFileName(), suffix, storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据时间生成文件名字
     */
    private static String generateFileName() {
        return FOLDER_NAME + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + "_";
    }

    public static Bitmap compressImageFromFile(String srcPath, float desWidth) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float desHeight = desWidth * h / w;
        int be = 1;
        if (w > h && w > desWidth) {
            be = (int) (newOpts.outWidth / desWidth);
        } else if (w < h && h > desHeight) {
            be = (int) (newOpts.outHeight / desHeight);
        }
        if (be <= 0) {
            be = 1;
        }
        newOpts.inSampleSize = be;
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;
        return BitmapFactory.decodeFile(srcPath, newOpts);
    }

    public static File compressImage(Bitmap image, int imageSize) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int options = 100;
        image.compress(Bitmap.CompressFormat.JPEG, options, outputStream);
        while (outputStream.toByteArray().length / 1024 > imageSize) {
            outputStream.reset();
            options -= 10;
            image.compress(Bitmap.CompressFormat.JPEG, options, outputStream);
        }
        File storageDir = new File(Environment.getExternalStorageDirectory(), "temp_data");
        if (!storageDir.exists() && !storageDir.mkdir()) {
            return null;
        }
        try {
            File file = File.createTempFile(generateFileName(), ".jpg", storageDir);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(outputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static Bitmap compressImage(Bitmap image, int imageSize) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        int options = 100;
//        image.compress(Bitmap.CompressFormat.JPEG, options, outputStream);
//        while (outputStream.toByteArray().length / 1024 > imageSize) {
//            outputStream.reset();
//            options -= 10;
//            image.compress(Bitmap.CompressFormat.JPEG, options, outputStream);
//        }
//        File storageDir = new File(Environment.getExternalStorageDirectory(), "temp_data");
//        if (!storageDir.exists() && !storageDir.mkdir()) {
//            return null;
//        }
//        try {
//            File file = File.createTempFile(generateFileName(), ".jpg", storageDir);
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write(outputStream.toByteArray());
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            return file;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    private static final String SD_PATH = "/sdcard/dskqxt/pic/";
    private static final String IN_PATH = "/dskqxt/pic/";

    /**
     * 随机生产文件名
     *
     * @return
     */
    /**
     * 保存bitmap到本地
     *
     * @param context
     * @param mBitmap
     * @return
     */
    public static void saveBitmap(Context context, Bitmap mBitmap) {
        DialogUtil.createLoadingDialog(context, "保存中...", false, null);
        String bitName = generateFileName() + ".JPEG";
        String fileName;
        File file;
        if (Build.BRAND.equals("Xiaomi")) { // 小米手机
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
        } else {  // Meizu 、Oppo
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + bitName;
        }
        file = new File(fileName);

        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if (mBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
                out.flush();
                out.close();
// 插入图库
                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), bitName, null);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        // 发送广播，通知刷新图库的显示
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));
        DialogUtil.dismissLoadingDialog();
        ToastUtil.newSafelyShow("保存成功");
    }
}