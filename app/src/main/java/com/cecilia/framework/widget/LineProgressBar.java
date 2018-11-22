package com.cecilia.framework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.cecilia.framework.R;


/**
 * @author stone
 */

public class LineProgressBar extends View {

    protected int progress;
    protected int maximum_progress = 100;

    protected Paint foregroundPaint, backgroundPaint, textPaint;
    protected float backgroundStrokeWidth = 3f, strokeWidth = 5f;
    protected int PADDING = 20;
    protected int color = getResources().getColor(R.color.color_main);
    protected int textColor = getResources().getColor(R.color.color_assist_first);
    protected int shadowColor = getResources().getColor(R.color.color_assist_second);
    protected int backgroundColor = Color.WHITE;
    protected int textSize = 26;
    protected Typeface typeface;
    protected String typeface_path = "Roboto-Light.ttf";
    protected int height, width;

    protected Context context;
    protected boolean isRoundEdge;
    protected boolean isShadowed;
    protected OnProgressTrackListener listener;

    private RectF rectP;

    public void setOnProgressTrackListener(OnProgressTrackListener listener) {
        this.listener = listener;
    }

    protected void init(Context context) {
        this.context = context;
        progress = 0;

        initBackgroundColor();
        initForegroundColor();
        initTextColor();
        rectP = new RectF();
    }

    public LineProgressBar(Context context) {
        super(context);
        init(context);
    }

    public LineProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypedArray(context, attrs);
        init(context);
    }

    public LineProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Progress, 0, 0);
        try {
            progress = (int) typedArray.getFloat(R.styleable.Progress_progress, progress);
            strokeWidth = typedArray.getDimension(R.styleable.Progress_stroke_width, strokeWidth);
            backgroundStrokeWidth = typedArray.getDimension(R.styleable.Progress_background_stroke_width, backgroundStrokeWidth);
            color = typedArray.getInt(R.styleable.Progress_progress_color, color);
            backgroundColor = typedArray.getInt(R.styleable.Progress_background_color, backgroundColor);
            textColor = typedArray.getInt(R.styleable.Progress_text_color, textColor);
            textSize = typedArray.getInt(R.styleable.Progress_text_size, textSize);
        } finally {
            typedArray.recycle();
        }
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);

        typeface = Typeface.createFromAsset(context.getAssets(), typeface_path);
    }

    protected void initBackgroundColor() {
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(backgroundStrokeWidth);
        if (isRoundEdge) {
            backgroundPaint.setStrokeCap(Paint.Cap.ROUND);
        }
        if (isShadowed) {
            backgroundPaint.setShadowLayer(3.0f, 0.0f, 2.0f, shadowColor);
        }
    }

    protected void initForegroundColor() {
        foregroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        foregroundPaint.setColor(color);
        foregroundPaint.setStyle(Paint.Style.STROKE);
        foregroundPaint.setStrokeWidth(strokeWidth);
        if (isRoundEdge) {
            foregroundPaint.setStrokeCap(Paint.Cap.ROUND);
        }
        if (isShadowed) {
            foregroundPaint.setShadowLayer(3.0f, 0.0f, 2.0f, shadowColor);
        }
    }

    protected void initTextColor() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setStrokeWidth(1f);
        textPaint.setTextSize(textSize);
        typeface = Typeface.createFromAsset(context.getAssets(), typeface_path);
        textPaint.setTypeface(typeface);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRects(canvas);
    }

    private void drawRects(Canvas canvas) {
        int nMiddle = height / 2;
        Rect bounds = new Rect();
        String text = "" + progress + "%";
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        float mt = textPaint.measureText(text) + 40 + text.length();
        int progressX = (int) ((width - mt) * progress / maximum_progress);

        rectP.left = getPaddingLeft();
        rectP.top = nMiddle;
        rectP.right = progressX;
        rectP.bottom = nMiddle;
        if (progress > 2) {
            canvas.drawRect(rectP, foregroundPaint);
        }
        if (progress < maximum_progress) {
            canvas.drawRect(rectP.width() + mt, nMiddle, width - getPaddingRight(), nMiddle, backgroundPaint);
        }
        canvas.drawText(text, progressX + 10, nMiddle + backgroundStrokeWidth, textPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        float xpad = (float) (getPaddingLeft() + getPaddingRight());
        float ypad = (float) (getPaddingTop() + getPaddingBottom());

        width = (int) (w - xpad);
        height = (int) (h - ypad);
        setMeasuredDimension(width, height);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setPadding(PADDING, 0, 0, 0);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        setProgressInView(progress);
    }

    private synchronized void setProgressInView(int progress) {
        this.progress = (progress <= maximum_progress) ? progress : maximum_progress;
        invalidate();
        trackProgressInView(progress);
    }

    private void trackProgressInView(int progress) {
        if (listener != null) {
            listener.onProgressUpdate(progress);
            if (progress >= maximum_progress) {
                listener.onProgressFinish();
            }
        }
    }

    public void resetProgress() {
        setProgress(0);
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        init(context);
    }

    public void setTypeface(String typefacePath) {
        this.typeface_path = typefacePath;
        init(context);
    }

    public int getProgressColor() {
        return color;
    }

    public void setProgressColor(int color) {
        this.color = color;
        init(context);
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        init(context);
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        init(context);
    }

    public void setProgressStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        init(context);
    }

    public void setBackgroundStrokeWidth(int strokeWidth) {
        this.backgroundStrokeWidth = strokeWidth;
        init(context);
    }

    public void setRoundEdge(boolean isRoundEdge) {
        this.isRoundEdge = isRoundEdge;
        init(context);
    }

    public void setShadow(boolean isShadowed) {
        this.isShadowed = isShadowed;
        init(context);
    }

    public interface OnProgressTrackListener {
        void onProgressFinish();

        void onProgressUpdate(int progress);
    }

}
