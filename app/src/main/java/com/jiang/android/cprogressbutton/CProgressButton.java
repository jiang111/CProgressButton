package com.jiang.android.cprogressbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by jiang on 2017/3/1.
 */

public class CProgressButton extends Button {

    private static final int STOREWIDTH = 1;
    private Drawable mBackground;
    private CProgressDrawable mProgressDrawable;
    private int mWidth;
    private int mHeight;
    private STATE mState = STATE.NORMAL;
    private boolean morphingCircle; //变形成圆中
    private boolean morphingNormal; //变形成正常状态中
    private float mFromCornerRadius=40;
    private float mToCornerRadius=90;
    private long mDuration=500;
    private int mProgress;
    private int mMaxProgress = 100;
    private int mStrokeColor;
    private int mStokeWidth = 0;
    private int mStokeWidthOut = 0;

    public enum STATE{
        PROGRESS,NORMAL
    }

    private static final String TAG = "CProgressButton";


    public CProgressButton(Context context) {
        this(context,null);
    }

    public CProgressButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CProgressButton,
                0, 0);
        try {
            mStrokeColor  = a.getInteger(R.styleable.CProgressButton_color, -1);
            mBackground  =  a.getDrawable(R.styleable.CProgressButton_drawable_xml);
            mStokeWidthOut = (int) a.getDimension(R.styleable.CProgressButton_stroke_width, -1);
            mFromCornerRadius = (int) a.getDimension(R.styleable.CProgressButton_radius, -1);
        } finally {
            a.recycle();
        }
        mStokeWidth = mStokeWidthOut*3;
    }




    public STATE getState() {
        return mState;
    }

    public void setState(STATE state) {
        if(state == mState)
            return;
        if(getWidth() == 0 || morphingCircle || morphingNormal)
            return;
        this.mState = state;
        if(mState == STATE.PROGRESS){
            morph2Circle();
        }else if(mState == STATE.NORMAL){
            morph2Normal();
        }

    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getWidth()  -getPaddingLeft() - getPaddingRight();
        mHeight = getHeight()  - getPaddingTop() - getPaddingRight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mState == STATE.NORMAL || (mState == STATE.PROGRESS && morphingCircle)) {
            setBound(0);
        }else{
            invalidate();
        }
    }

    private void setBound(int padding){
        if(mWidth == 0){
            mWidth = getWidth()  -getPaddingLeft() - getPaddingRight();
        }
        if(mHeight == 0){
            mHeight = getHeight()  - getPaddingTop() - getPaddingRight();
        }
        mBackground.setBounds(getPaddingLeft()+padding,getPaddingTop(),getPaddingLeft()+mWidth- padding,getPaddingTop()+mHeight);
        invalidate();

    }

    public void setProgress(int progress){
        mProgress = progress;
        if(morphingCircle || morphingNormal)
            return;
        if(mProgress >= mMaxProgress){
            mProgress = mMaxProgress;
        }
        if(mProgress<=0){
            mProgress = 0;
        }
        invalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mState == STATE.NORMAL || (mState == STATE.PROGRESS && morphingCircle)) {
            mBackground.draw(canvas);
        }else if(mState == STATE.PROGRESS && !morphingCircle){
            if (mProgressDrawable == null) {
                int offset = (mWidth - mHeight) / 2+getPaddingLeft();
                int size = mHeight;
                mProgressDrawable = new CProgressDrawable(getContext(),size, mStokeWidth,mStokeWidthOut, mStrokeColor);
                int left = offset;
                mProgressDrawable.setBounds(left, getPaddingTop(),left+ mHeight, getPaddingTop()+mHeight);
            }
            float sweepAngle = (360f / mMaxProgress) * mProgress;
            mProgressDrawable.setSweepAngle(sweepAngle);
            mProgressDrawable.draw(canvas);

        }
    }

    private void morph2Normal() {

        ObjectAnimator cornerAnimation =
                ObjectAnimator.ofFloat(mBackground, "cornerRadius", mToCornerRadius,mFromCornerRadius);

        final int start = (mWidth-mHeight)/2;
        ValueAnimator animator = ValueAnimator.ofInt(start,0);
        animator.setDuration(mDuration)
                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int)animation.getAnimatedValue();
                        setBound(value);
                    }


                });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(mDuration);
        animatorSet.playTogether(animator, cornerAnimation);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                morphingNormal = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                morphingNormal = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                morphingNormal = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }


    private void morph2Circle(){

        ObjectAnimator cornerAnimation =
                ObjectAnimator.ofFloat(mBackground, "cornerRadius", mFromCornerRadius, mToCornerRadius);

        final int end =(mWidth-mHeight)/2;
        ValueAnimator animator = ValueAnimator.ofInt(0,end);
        animator.setDuration(mDuration)
                .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int)animation.getAnimatedValue();
                        setBound(value);
                    }
                });


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(mDuration);
        animatorSet.playTogether(animator, cornerAnimation);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setText("");
                morphingCircle = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setText("");
                morphingCircle = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                morphingCircle = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();


    }


    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.mProgress = mProgress;
        savedState.morphingNormal = morphingNormal;
        savedState.morphingCircle = morphingCircle;

        return savedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState savedState = (SavedState) state;
            mProgress = savedState.mProgress;
            morphingNormal = savedState.morphingNormal;
            morphingCircle = savedState.morphingCircle;
            super.onRestoreInstanceState(savedState.getSuperState());
            setProgress(mProgress);
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    static class SavedState extends BaseSavedState {

        private boolean morphingNormal;
        private boolean morphingCircle;
        private int mProgress;

        public SavedState(Parcelable parcel) {
            super(parcel);
        }

        private SavedState(Parcel in) {
            super(in);
            mProgress = in.readInt();
            morphingCircle = in.readInt() == 1;
            morphingNormal = in.readInt() == 1;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(mProgress);
            out.writeInt(morphingNormal ? 1 : 0);
            out.writeInt(morphingCircle ? 1 : 0);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {

            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
