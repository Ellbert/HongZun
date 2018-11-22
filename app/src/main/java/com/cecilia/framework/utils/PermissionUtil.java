package com.cecilia.framework.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态权限工具类
 *
 * @author stone
 */

public class PermissionUtil {

    private PermissionUtil() {
        throw new AssertionError();
    }

    /**
     * 对Android 6.0及以上进行权限检查，没有权限时就进行申请
     *
     * @param activity    当前要检查权限的Activity，不要调用{@link ViewUtil#getContext()}进行强转
     * @param requestCode 请求权限的请求码（用于请求成功后，判断是哪次权限请求成功）
     * @param permissions 所需检查的权限（例子：{@link Manifest.permission#CAMERA}）
     * @return 权限是否满足
     */
    public static boolean checkRequestPermissionInActivity(Activity activity, int requestCode, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null && permissions.length > 0) {
            List<String> permissionList = new ArrayList<>();
            for (String permission : permissions) {
                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(ViewUtil.getContext(), permission)) {
                    permissionList.add(permission);
                }
            }
            if (permissionList.size() != 0) {
                ActivityCompat.requestPermissions(activity, permissionList.toArray(new String[0]), requestCode);
                return false;
            }
        }
        return true;
    }

    /**
     * 对Android 6.0及以上进行权限检查，没有权限时就进行申请
     *
     * @param fragment    当前要检查权限的Fragment
     * @param requestCode 请求权限的请求码（用于请求成功后，判断是哪次权限请求成功）
     * @param permissions 所需检查的权限（例子：{@link Manifest.permission#CAMERA}）
     * @return 权限是否满足
     */
    public static boolean checkRequestPermissionInFragment(@NonNull Fragment fragment, int requestCode, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null && permissions.length > 0) {
            List<String> permissionList = new ArrayList<>();
            for (String permission : permissions) {
                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(ViewUtil.getContext(), permission)) {
                    permissionList.add(permission);
                }
            }
            if (permissionList.size() != 0) {
                fragment.requestPermissions(permissionList.toArray(new String[0]), requestCode);
                return false;
            }
        }
        return true;
    }

    /**
     * 对Android 6.0及以上进行权限检查
     *
     * @param permissions 所需检查的权限（例子：{@link Manifest.permission#CAMERA}）
     * @return 权限是否满足
     */
    public static boolean onlyCheckPermission(String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null && permissions.length > 0) {
            for (String permission : permissions) {
                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(ViewUtil.getContext(), permission)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 将代码串列表的权限名转化为文本字符串<br/>
     * 如 android.permission.READ_CONTACTS => 读取联系人
     */
    public static String getPermissionStr(List<String> permissions) {
        StringBuilder permissionStr = new StringBuilder();
        for (String permission : permissions) {
            switch (permission) {
                case Manifest.permission.WRITE_CONTACTS:
                    permissionStr.append("新建/修改/删除联系人、");
                    break;
                //case Manifest.permission.GET_ACCOUNTS:
                //    break;
                case Manifest.permission.READ_CONTACTS:
                    permissionStr.append("读取联系人、");
                    break;
                case Manifest.permission.READ_CALL_LOG:
                    permissionStr.append("读取通话记录、");
                    break;
                case Manifest.permission.READ_PHONE_STATE:
                    permissionStr.append("读取电话状态、");
                    break;
                case Manifest.permission.CALL_PHONE:
                    permissionStr.append("拨打电话、");
                    break;
                case Manifest.permission.WRITE_CALL_LOG:
                    permissionStr.append("新建/修改/删除通话记录、");
                    break;
                //case Manifest.permission.USE_SIP:
                //    break;
                //case Manifest.permission.PROCESS_OUTGOING_CALLS:
                //    permissionStr.append("处理拨出电话、");
                //    break;
                //case Manifest.permission.ADD_VOICEMAIL:
                //    break;
                case Manifest.permission.READ_CALENDAR:
                    permissionStr.append("读取日历、");
                    break;
                case Manifest.permission.WRITE_CALENDAR:
                    permissionStr.append("新建/修改/删除日历、");
                    break;
                case Manifest.permission.CAMERA:
                    permissionStr.append("调用摄像头、");
                    break;
                //case Manifest.permission.BODY_SENSORS:
                //    break;
                case Manifest.permission.ACCESS_FINE_LOCATION:
                case Manifest.permission.ACCESS_COARSE_LOCATION:
                    if (!permissionStr.toString().contains("读取位置信息、")) {
                        permissionStr.append("读取位置信息、");
                    }
                    break;
                case Manifest.permission.READ_EXTERNAL_STORAGE:
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    if (!permissionStr.toString().contains("访问存储、")) {
                        permissionStr.append("访问存储、");
                    }
                    break;
                case Manifest.permission.RECORD_AUDIO:
                    permissionStr.append("启用录音、");
                    break;
                //case Manifest.permission.RECEIVE_WAP_PUSH:
                //    break;
                case Manifest.permission.READ_SMS:
                case Manifest.permission.RECEIVE_MMS:
                case Manifest.permission.RECEIVE_SMS:
                    if (!permissionStr.toString().contains("读取短信彩信、")) {
                        permissionStr.append("读取短信彩信、");
                    }
                    break;
                case Manifest.permission.SEND_SMS:
                    permissionStr.append("发送短信、");
                    break;
            }
        }
        return permissionStr.deleteCharAt(permissionStr.length() - 1).append("权限").toString();
    }

}
