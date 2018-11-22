package com.cecilia.framework.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;;import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseAdapter;
import com.cecilia.framework.widget.AutoSplitTextView;

/**
 * 对话框工具类
 */
public class DialogUtil {

    private static Dialog sLoadingDialog;

    private DialogUtil() {
        throw new AssertionError();
    }

    /**
     * 创建加载进度框
     *
     * @param context 请使用当前的Context，不要调用{@link ViewUtil#getContext()}
     */
    public static void createLoadingDialog(Context context, String msg, boolean isCanCancelable,
                                           DialogInterface.OnCancelListener cancelEvent) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null); // 得到加载view
        LinearLayout llLoading = (LinearLayout) view.findViewById(R.id.ll_loading); // 加载布局
        // main.xml中的ImageView
        ImageView ivLoading = (ImageView) view.findViewById(R.id.iv_loading);
        // 提示文字
        TextView tvLoadingTip = (TextView) view.findViewById(R.id.tv_loading_tip);
        // 加载动画
        Animation rotationAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_loading);
        // 使用ImageView显示动画
        ivLoading.startAnimation(rotationAnimation);

        if (TextUtils.isEmpty(msg)) {
            tvLoadingTip.setVisibility(View.GONE);
        } else {
            tvLoadingTip.setVisibility(View.VISIBLE);
            tvLoadingTip.setText(msg); // 设置加载信息
        }
        // 创建自定义样式dialog
        sLoadingDialog = new Dialog(context, R.style.theme_dialog_loading);
        sLoadingDialog.setCancelable(isCanCancelable); // 设置可不可以用“返回键”取消
        if (isCanCancelable) {
            if (cancelEvent == null) {
                sLoadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        AsynchronousUtil.clearObserver();
                        NetworkUtil.clearObserver();
                    }
                });
            } else {
                sLoadingDialog.setOnCancelListener(cancelEvent);
            }
        }
        sLoadingDialog.setContentView(llLoading, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)); // 设置布局
        sLoadingDialog.show();
    }

    /**
     * 销毁加载进度框
     */
    public static void dismissLoadingDialog() {
        if (sLoadingDialog != null && sLoadingDialog.isShowing()) {
            sLoadingDialog.dismiss();
            sLoadingDialog = null;
        }
    }

    /**
     * 创建列表对话框
     *
     * @param context 请使用当前的Context，不要调用{@link ViewUtil#getContext()}
     */
    public static Dialog createListDialog(Context context, String title,
                                          String positiveStr, OnDialogViewButtonClickListener positiveEvent,
                                          String negativeStr, OnDialogViewButtonClickListener negativeEvent,
                                          BaseAdapter adapter, AdapterView.OnItemClickListener adapterEvent,
                                          DialogInterface.OnCancelListener cancelEvent) {
        return createDialog(context, title, null, -1, positiveStr, positiveEvent,
                negativeStr, negativeEvent, adapter, adapterEvent, cancelEvent);
    }

    /**
     * 创建输入对话框
     *
     * @param context   请使用当前的Context，不要调用{@link ViewUtil#getContext()}
     * @param inputType 请使用{@link android.text.InputType}枚举里的值
     */
    public static Dialog createInputDialog(Context context, String title, int inputType,
                                           String positiveStr, OnDialogEditButtonClickListener positiveEvent,
                                           String negativeStr, OnDialogEditButtonClickListener negativeEvent,
                                           DialogInterface.OnCancelListener cancelEvent) {
        return createDialog(context, title, null, inputType, positiveStr, positiveEvent, negativeStr,
                negativeEvent, null, null, cancelEvent);
    }

    /**
     * 创建提示对话框
     *
     * @param context 请使用当前的Context，不要调用{@link ViewUtil#getContext()}
     */
    public static Dialog createPromptDialog(Context context, String title, String message,
                                            String positiveStr, OnDialogViewButtonClickListener positiveEvent,
                                            String negativeStr, OnDialogViewButtonClickListener negativeEvent,
                                            DialogInterface.OnCancelListener cancelEvent) {
        return createDialog(context, title, message, -1, positiveStr, positiveEvent, negativeStr,
                negativeEvent, null, null, cancelEvent);
    }

    private static Dialog createDialog(Context context, String title, String message, int inputType,
                                       String positiveStr, View.OnClickListener positiveEvent,
                                       String negativeStr, View.OnClickListener negativeEvent,
                                       BaseAdapter adapter, AdapterView.OnItemClickListener adapterEvent,
                                       DialogInterface.OnCancelListener cancelEvent) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_prompt, null);
        Dialog dialog = new AlertDialog.Builder(context, R.style.theme_dialog)
                .setView(view)
                .setOnCancelListener(cancelEvent)
                .setCancelable(true).create();

        ((TextView) view.findViewById(R.id.tv_title)).setText(title);
        AutoSplitTextView tvMessage = (AutoSplitTextView) view.findViewById(R.id.tv_message);
        TextView tvPositive = (TextView) view.findViewById(R.id.tv_positive);
        TextView tvNegative = (TextView) view.findViewById(R.id.tv_negative);
        ListView lvChoice = (ListView) view.findViewById(R.id.lv_choice);
        EditText etInput = (EditText) view.findViewById(R.id.et_input);

        // 判断是不是要显示内容
        if (TextUtils.isEmpty(message)) {
            tvMessage.setVisibility(View.GONE);
        } else {
            tvMessage.setText(message);
            tvMessage.setVisibility(View.VISIBLE);
        }
        // 判断要不要显示输入框
        if (inputType < 0) {
            etInput.setVisibility(View.GONE);
        } else {
            etInput.setInputType(inputType);
            etInput.setVisibility(View.VISIBLE);
        }
        // 判断要不要显示列表
        if (adapter == null) {
            lvChoice.setVisibility(View.GONE);
        } else {
            lvChoice.setVisibility(View.VISIBLE);
            // 限定列表里一次性最多展示多少项
            if (adapter.getCount() > 5) {
                ViewGroup.LayoutParams layoutParams = lvChoice.getLayoutParams();
                layoutParams.height = DensityUtil.dp2px(context, 40) * 5 + DensityUtil.dp2px(context, 2) * 4;
                lvChoice.setLayoutParams(layoutParams);
            }
            lvChoice.setAdapter(adapter);
            if (adapterEvent != null) {
                lvChoice.setOnItemClickListener(adapterEvent);
            }
        }
        tvPositive.setText(positiveStr);
        tvPositive.setTag(R.id.tag_first, dialog);
        if (inputType >= 0) {
            tvPositive.setTag(R.id.tag_second, etInput);
            tvPositive.setOnClickListener(positiveEvent != null ? positiveEvent : new OnDialogEditButtonClickListener() {
                @Override
                public boolean onClick(String txt) {
                    return false;
                }
            });
        } else {
            tvPositive.setOnClickListener(positiveEvent != null ? positiveEvent : new OnDialogViewButtonClickListener() {
                @Override
                public boolean onClick() {
                    return false;
                }
            });
        }
        // 判断要不要显示第二个按钮
        if (!TextUtils.isEmpty(negativeStr)) {
            tvNegative.setVisibility(View.VISIBLE);
            view.findViewById(R.id.v_div).setVisibility(View.VISIBLE);
            tvNegative.setText(negativeStr);
            tvNegative.setTag(R.id.tag_first, dialog);
            if (inputType >= 0) {
                tvNegative.setTag(R.id.tag_second, etInput);
                tvNegative.setOnClickListener(negativeEvent != null ? negativeEvent : new OnDialogEditButtonClickListener() {
                    @Override
                    public boolean onClick(String txt) {
                        return false;
                    }
                });
            } else {
                tvNegative.setOnClickListener(negativeEvent != null ? negativeEvent : new OnDialogViewButtonClickListener() {
                    @Override
                    public boolean onClick() {
                        return false;
                    }
                });
            }
        } else {
            tvNegative.setVisibility(View.GONE);
            view.findViewById(R.id.v_div).setVisibility(View.GONE);
        }
        return dialog;
    }

    /**
     * 创建权限申请失败对话框
     *
     * @param context 请使用当前的Context，不要调用{@link ViewUtil#getContext()}
     */
    public static Dialog createPermissionExceptionDialog(final Context context, String permissionExceptionCause, OnDialogViewButtonClickListener negativeEvent) {
        return createPromptDialog(context, "权限获取失败提示", "该操作需要赋予" + permissionExceptionCause + "，请到“设置”>“应用”>“权限”中配置权限。",
                ViewUtil.getString(R.string.ok), new OnDialogViewButtonClickListener() {
                    @Override
                    public boolean onClick() {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + context.getPackageName()));
                        context.startActivity(intent);
                        return false;
                    }
                }, ViewUtil.getString(R.string.cancel), negativeEvent, null);
    }

    /**
     * DialogUtil专属按钮点击监听，其他地方请勿调用
     */
    public static abstract class OnDialogViewButtonClickListener implements View.OnClickListener {
        @Override
        public final void onClick(View view) {
            if (!onClick() && view.getTag(R.id.tag_first) instanceof Dialog) {
                ((Dialog) view.getTag(R.id.tag_first)).cancel();
            }
        }

        /**
         * @return 返回是否不关闭对话框（false-关闭，true-不关闭）
         */
        public abstract boolean onClick();
    }

    /**
     * DialogUtil专属按钮点击监听，其他地方请勿调用
     */
    public static abstract class OnDialogEditButtonClickListener implements View.OnClickListener {
        @Override
        public final void onClick(View view) {
            String string = "";
            if (view.getTag(R.id.tag_second) instanceof EditText) {
                string = ((EditText) view.getTag(R.id.tag_second)).getText().toString();
            }
            if (!onClick(string) && view.getTag(R.id.tag_first) instanceof Dialog) {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(((EditText) view.getTag(R.id.tag_second)).getApplicationWindowToken(), 0);
                }
                ((Dialog) view.getTag(R.id.tag_first)).cancel();
            }
        }

        /**
         * @param txt 输入框里的文本
         * @return 返回是否不关闭对话框（false-关闭，true-不关闭）
         */
        public abstract boolean onClick(String txt);
    }

}
