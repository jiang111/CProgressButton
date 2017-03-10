package com.jiang.android.pbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * Created by jiang on 2017/3/1.
 */

public class CProgressDrawable extends Drawable {

    private static final String TAG = "CircularProgressDrawabl";
    private float mSweepAngle;
    private float mStartAngle;
    private int mSize;
    private int mStrokeWidth;
    private int mStrokeWidthOut;
    private int mStrokeColor;
    private Context mContext;
    private RectF mMiddleRect;

    public CProgressDrawable(Context context,int size, int strokeWidth,int stokenWidthOut, int strokeColor) {
        mSize = size;
        this.mContext = context;
        this.mStrokeWidthOut = stokenWidthOut;
        mStrokeWidth = strokeWidth;
        mStrokeColor = strokeColor;
        mStartAngle = -90;
        mSweepAngle = 0;
    }

    public void setSweepAngle(float sweepAngle) {
        mSweepAngle = sweepAngle;
    }

    public int getSize() {
        return mSize;
    }

    @Override
    public void draw(Canvas canvas) {
        final Rect bounds = getBounds();

        if (mPath == null) {
            mPath = new Path();
        }


        mPath.reset();
        mPath.addArc(getRect(mStrokeWidthOut), 0, 360);
        mPath.offset(bounds.left, bounds.top);
        canvas.drawPath(mPath, createPaint(mStrokeWidthOut));

        mPath.reset();
        mPath.addArc(getRect(mStrokeWidth), mStartAngle, mSweepAngle);
        mPath.offset(bounds.left, bounds.top);
        canvas.drawPath(mPath, createPaint(mStrokeWidth));

        mPath.reset();
        mPath.addRect(getRectInMiddle(), Path.Direction.CCW);
        mPath.offset(bounds.left, bounds.top);
        canvas.drawPath(mPath, createPaint(0));

    }

    private RectF getRectInMiddle() {
        int size = getSize();
        mMiddleRect = new RectF(size/3, size/3, size - size/3, size - size/3);
        return mMiddleRect;

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    @Override
    public int getOpacity() {
        return 1;
    }

    private RectF mRectF;
    private Paint mPaint;
    private Path mPath;

    private RectF getRect(int stoken) {
        int size = getSize();
        int index = stoken / 2;
        mRectF = new RectF(index, index, size - index, size - index);
        return mRectF;
    }

    private Paint createPaint(int stokenWidth) {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(mStrokeColor);
        }
        if(stokenWidth == 0){
            mPaint.setStyle(Paint.Style.FILL);
        }else{
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(stokenWidth);
        }


        return mPaint;
    }


}
