package com.dwz.threadapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author dongweizhou
 * @createTime 2019/9/2
 * @describe
 * @DWZ
 */
public class SlideImageView extends View {

    private Paint mPaint = new Paint();
    private Paint mPaint1 = new Paint();
    private Paint mPaint2 = new Paint();
    private Paint mPaint3 = new Paint();
    private Paint mPaint4 = new Paint();
    Path path = new Path();
    Path path1 = new Path();
    Path path2 = new Path();
    Path path3 = new Path();
    private RectF rect;
    private RectF rect2;
    private int width,heigth;

    public float drgree=60;
    private Paint mTextPaint = new Paint();

    public float getDrgree() {
        return drgree;
    }

    public void setDrgree(float drgree) {
        this.drgree = drgree;
    }

    public SlideImageView(Context context) {
        super(context);
        init();
    }

    public SlideImageView(Context context,  AttributeSet attrs) {
        super(context, attrs);
         init();
    }


    public SlideImageView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SlideImageView(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect,0,drgree,true,mPaint);
        drawText(canvas,50,"董维周", 60);
        canvas.drawArc(rect,60,drgree,true,mPaint1);
        drawText(canvas,100,"董维周", 60);
        canvas.drawArc(rect,120,120,true,mPaint2);
        drawText(canvas,210,"董维周", 12);
        canvas.drawArc(rect,240,120,true,mPaint3);
        drawText(canvas,320,"董维周", 12);
    }


    private void init() {
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setColor(getResources().getColor(R.color.empty,null));
        mPaint.setStrokeWidth(60.0f);
        mPaint.setStyle(Paint.Style.FILL);
        // 防止锯齿
        mPaint.setAntiAlias(true);
        //防止抖动
        mPaint.setDither(true);


        mPaint1.setColor(getResources().getColor(R.color.blk22,null));
        mPaint1.setStrokeWidth(60.0f);
        mPaint1.setStyle(Paint.Style.FILL);
        // 防止锯齿
        mPaint1.setAntiAlias(true);
        //防止抖动
        mPaint1.setDither(true);


        mPaint2.setColor(getResources().getColor(R.color.blk,null));
        mPaint2.setStrokeWidth(60.0f);
        mPaint2.setStyle(Paint.Style.FILL);
        // 防止锯齿
        mPaint2.setAntiAlias(true);
        //防止抖动
        mPaint2.setDither(true);

        mPaint3.setColor(getResources().getColor(R.color.black,null));
        mPaint3.setStrokeWidth(60.0f);
        mPaint3.setStyle(Paint.Style.FILL);
        // 防止锯齿
        mPaint3.setAntiAlias(true);
        //防止抖动
        mPaint3.setDither(true);

        mTextPaint.setColor(getResources().getColor(R.color.blk,null));
        mTextPaint.setStrokeWidth(2.0f);
        mTextPaint.setStyle(Paint.Style.STROKE);
        // 防止锯齿
        mTextPaint.setAntiAlias(true);
        //防止抖动
        mTextPaint.setDither(true);



        mPaint4.setColor(getResources().getColor(R.color.empty,null));
        mPaint4.setStrokeWidth(1.0f);
        mPaint4.setStyle(Paint.Style.FILL);
        // 防止锯齿
        mPaint4.setAntiAlias(true);
        //防止抖动
        mPaint4.setDither(true);


        width = getResources().getDisplayMetrics().widthPixels;
        heigth = getResources().getDisplayMetrics().heightPixels;
        rect = new RectF((float) width/2-150,(float) heigth/2-150,(float) width/2+150,(float) heigth/2+150);
    }


    private void drawText(Canvas mCanvas, float textAngle, String kinds, float needDrawAngle) {
        Rect rect = new Rect();
        mTextPaint.setTextSize(15);
        mTextPaint.getTextBounds(kinds, 0, kinds.length(), rect);
        double mRadius = 160;
        float minAngle = 160;
        if (textAngle >= 0 && textAngle <= 90) { //画布坐标系第一象限(数学坐标系第四象限)
            if (needDrawAngle < minAngle) { //如果小于某个度数,就把文字画在饼状图外面
                mCanvas.drawText(kinds, (float) (mRadius * 1.2 * Math.cos(Math.toRadians(textAngle))+width/2), (float) (mRadius * 1.2 * Math.sin(Math.toRadians(textAngle))+heigth/2)+rect.height()/2, mTextPaint);
            } else {
                mCanvas.drawText(kinds, (float) (mRadius * 0.75 * Math.cos(Math.toRadians(textAngle))+width/2), (float) (mRadius * 0.75 * Math.sin(Math.toRadians(textAngle))+heigth/2)+rect.height()/2, mTextPaint);
            }
        } else if (textAngle > 90 && textAngle <= 180) { //画布坐标系第二象限(数学坐标系第三象限)
            if (needDrawAngle < minAngle) {
                mCanvas.drawText(kinds, (float) (-mRadius * 1.2 * Math.cos(Math.toRadians(180 - textAngle))+width/2), (float) (mRadius * 1.2 * Math.sin(Math.toRadians(180 - textAngle))+heigth/2)+rect.height()/2, mTextPaint);
            } else {
                mCanvas.drawText(kinds, (float) (-mRadius * 0.75 * Math.cos(Math.toRadians(180 - textAngle))+width/2), (float) (mRadius * 0.75 * Math.sin(Math.toRadians(180 - textAngle))+heigth/2)+rect.height()/2, mTextPaint);
            }
        } else if (textAngle > 180 && textAngle <= 270) { //画布坐标系第三象限(数学坐标系第二象限)
            if (needDrawAngle < minAngle) {
                mCanvas.drawText(kinds, (float) (-mRadius * 1.2 * Math.cos(Math.toRadians(textAngle - 180))+width/2), (float) (-mRadius * 1.2 * Math.sin(Math.toRadians(textAngle - 180))+heigth/2)+rect.height()/2, mTextPaint);
            } else {
                mCanvas.drawText(kinds, (float) (-mRadius * 0.75 * Math.cos(Math.toRadians(textAngle - 180))+width/2), (float) (-mRadius * 0.75 * Math.sin(Math.toRadians(textAngle - 180))+heigth/2)+rect.height()/2, mTextPaint);
            }
        } else { //画布坐标系第四象限(数学坐标系第一象限)
            if (needDrawAngle < minAngle) {
                mCanvas.drawText(kinds, (float) (mRadius * 1.2 * Math.cos(Math.toRadians(360 - textAngle))+width/2), (float) (-mRadius * 1.2 * Math.sin(Math.toRadians(360 - textAngle))+heigth/2)+rect.height()/2, mTextPaint);
            } else {
                mCanvas.drawText(kinds, (float) (mRadius * 0.75 * Math.cos(Math.toRadians(360 - textAngle))+width/2), (float) (-mRadius * 0.75 * Math.sin(Math.toRadians(360 - textAngle))+heigth/2)+rect.height()/2, mTextPaint);
            }
        }

    }
}
