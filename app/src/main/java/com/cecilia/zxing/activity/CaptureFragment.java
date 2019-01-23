package com.cecilia.zxing.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;


import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.utils.PermissionUtil;
import com.cecilia.zxing.camera.CameraManager;
import com.cecilia.zxing.decoding.CaptureActivityHandler;
import com.cecilia.zxing.decoding.InactivityTimer;
import com.cecilia.zxing.view.ViewfinderView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.io.IOException;
import java.util.Vector;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 自定义实现的扫描Fragment
 */
public class CaptureFragment extends BaseFragment implements SurfaceHolder.Callback {


    @BindView(R.id.viewfinder_view)
    ViewfinderView viewfinderView;
    @BindView(R.id.preview_view)
    SurfaceView surfaceView;
    @BindView(R.id.iv_scan_back)
    ImageView mTvBack;
    @BindView(R.id.iv_light_close)
    ImageView mCloseLight;
    @BindView(R.id.iv_light_open)
    ImageView mOpenLight;

    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
    private SurfaceHolder surfaceHolder;
    private CodeUtils.AnalyzeCallback analyzeCallback;
    private Camera camera;
    private Camera.Parameters parameters;

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public View initViews(LayoutInflater inflater) {
        Bundle bundle = getArguments();
        View view = null;
        if (bundle != null) {
            int layoutId = bundle.getInt(CodeUtils.LAYOUT_ID);
            if (layoutId != -1) {
                view = inflater.inflate(layoutId, null);
            }
        }

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_capture, null);
        }
        return view;
    }

    @Override
    public void initData() {
        CameraManager.init(getActivity().getApplication());
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this.getActivity());
        surfaceHolder = surfaceView.getHolder();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.iv_scan_back,R.id.iv_light_close,R.id.iv_light_open})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_scan_back:
                CaptureFragment.this.getActivity().finish();
                break;
            case R.id.iv_light_open:
                parameters = camera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                break;
            case R.id.iv_light_close:
                parameters = camera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
                break;
            default:
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;
        playBeep = true;
        AudioManager audioService = (AudioManager) getActivity().getSystemService(getActivity().AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        inactivityTimer.shutdown();
        super.onDestroy();
    }


    /**
     * Handler scan result
     *
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();

        if (result == null || TextUtils.isEmpty(result.getText())) {
            if (analyzeCallback != null) {
                analyzeCallback.onAnalyzeFailed();
            }
        } else {
            if (analyzeCallback != null) {
                analyzeCallback.onAnalyzeSuccess(barcode, result.getText());
            }
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {

        if (PermissionUtil.checkRequestPermissionInFragment(this, 001, Manifest.permission.CAMERA)) {
            try {
                CameraManager.get().openDriver(surfaceHolder);
                camera = CameraManager.get().getCamera();
            } catch (Exception e) {
                displayFrameworkBugMessageAndExit();
                return;
            }
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats, characterSet, viewfinderView);
        }
    }

    private void displayFrameworkBugMessageAndExit() {
        // camera error
        AlertDialog.Builder builder = new AlertDialog.Builder(CaptureFragment.this.getActivity());
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("未开启相机权限，可在设置-权限管理授权");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                CaptureFragment.this.getActivity().finish();
            }

        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                CaptureFragment.this.getActivity().finish();
            }
        });
        builder.show();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
        if (camera != null) {
            if (CameraManager.get().isPreviewing()) {
                if (!CameraManager.get().isUseOneShotPreviewCallback()) {
                    camera.setPreviewCallback(null);
                }
                camera.stopPreview();
                CameraManager.get().getPreviewCallback().setHandler(null, 0);
                CameraManager.get().getAutoFocusCallback().setHandler(null, 0);
                CameraManager.get().setPreviewing(false);
            }
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    public void setAnalyzeCallback(CodeUtils.AnalyzeCallback analyzeCallback) {
        this.analyzeCallback = analyzeCallback;
    }

    @Override
    public void onRequestPermissionsSucceed(int requestCode) {
        super.onRequestPermissionsSucceed(requestCode);
        switch (requestCode) {
            case 001:
                try {
                    CameraManager.get().openDriver(surfaceHolder);
                    camera = CameraManager.get().getCamera();
                } catch (Exception e) {
                    displayFrameworkBugMessageAndExit();
                    return;
                }
                break;
        }
    }

    @Override
    protected void onPermissionExceptionDialogCancel() {
        super.onPermissionExceptionDialogCancel();
    }

}
