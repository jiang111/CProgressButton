package com.jiang.android.cprogressbutton;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1();
        button2();
        button3();


    }

    private void button3() {
        final CProgressButton progressButton = (CProgressButton)findViewById(R.id.btn3);
        progressButton.setbgDrawable(R.drawable.bounder3,40);
        progressButton.setStroke(1,R.color.black);


        final TextView tv = (TextView) findViewById(R.id.state3);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
                if(progressButton.getState() == CProgressButton.STATE.NORMAL){
                    progressButton.setState(CProgressButton.STATE.PROGRESS);
                    valueAnimator.setDuration(5000);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int value = (int)animation.getAnimatedValue();
                            tv.setText("state progress:"+value);
                            if(value == 100){
                                progressButton.setState(CProgressButton.STATE.NORMAL);
                                tv.setText("state normal");
                                progressButton.setText("Finish");
                            }
                            progressButton.setProgress(value);
                        }
                    });
                    valueAnimator.start();
                }else{
                    valueAnimator.cancel();
                    progressButton.setState(CProgressButton.STATE.NORMAL);
                    tv.setText("state normal");
                    progressButton.setText("Continue");
                }

            }
        });
    }

    private void button2() {
        final CProgressButton progressButton = (CProgressButton)findViewById(R.id.btn2);
        progressButton.setbgDrawable(R.drawable.bounder2,40);
        progressButton.setStroke(1,R.color.colorPrimary);


        final TextView tv = (TextView) findViewById(R.id.state2);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
                if(progressButton.getState() == CProgressButton.STATE.NORMAL){
                    progressButton.setState(CProgressButton.STATE.PROGRESS);
                    valueAnimator.setDuration(5000);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int value = (int)animation.getAnimatedValue();
                            tv.setText("state progress:"+value);
                            if(value == 100){
                                progressButton.setState(CProgressButton.STATE.NORMAL);
                                tv.setText("state normal");
                                progressButton.setText("Finish");
                            }
                            progressButton.setProgress(value);
                        }
                    });
                    valueAnimator.start();
                }else{
                    valueAnimator.cancel();
                    progressButton.setState(CProgressButton.STATE.NORMAL);
                    tv.setText("state normal");
                    progressButton.setText("Continue");
                }

            }
        });
    }

    private void button1() {
        final CProgressButton progressButton = (CProgressButton)findViewById(R.id.btn);
        progressButton.setbgDrawable(R.drawable.bounder,40);
        progressButton.setStroke(1,R.color.colorAccent);


        final TextView tv = (TextView) findViewById(R.id.state);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
                if(progressButton.getState() == CProgressButton.STATE.NORMAL){
                    progressButton.setState(CProgressButton.STATE.PROGRESS);
                    valueAnimator.setDuration(5000);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int value = (int)animation.getAnimatedValue();
                            tv.setText("state progress:"+value);
                            if(value == 100){
                                progressButton.setState(CProgressButton.STATE.NORMAL);
                                tv.setText("state normal");
                                progressButton.setText("Finish");
                            }
                            progressButton.setProgress(value);
                        }
                    });
                    valueAnimator.start();
                }else{
                    valueAnimator.cancel();
                    progressButton.setState(CProgressButton.STATE.NORMAL);
                    tv.setText("state normal");
                    progressButton.setText("Continue");
                }

            }
        });
    }
}
