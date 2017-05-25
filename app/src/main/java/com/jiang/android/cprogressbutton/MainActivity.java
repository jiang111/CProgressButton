package com.jiang.android.cprogressbutton;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jiang.android.pbutton.CProgressButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CProgressButton.initStatusString(new String[]{"download","pause","complete","error","delete"});

        button1();
        button2();
        button3();


    }

    private void button3() {
        final CProgressButton progressButton = (CProgressButton)findViewById(R.id.btn3);


        final TextView tv = (TextView) findViewById(R.id.state3);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
                if(progressButton.getState() == CProgressButton.STATE.NORMAL){
                    progressButton.startDownLoad();
                    valueAnimator.setDuration(5000);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int value = (int)animation.getAnimatedValue();
                            tv.setText("state progress:"+value);
                            if(value == 100){
                                progressButton.normal(2);
                                tv.setText("state normal");
                            }
                            progressButton.download(value);
                        }
                    });
                    valueAnimator.start();
                }else{
                    valueAnimator.cancel();
                    progressButton.normal(0);
                    tv.setText("state normal");
                }

            }
        });
    }

    private void button2() {
        final CProgressButton progressButton = (CProgressButton)findViewById(R.id.btn2);


        final TextView tv = (TextView) findViewById(R.id.state2);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
                if(progressButton.getState() == CProgressButton.STATE.NORMAL){
                    valueAnimator.setDuration(5000);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int value = (int)animation.getAnimatedValue();
                            tv.setText("state progress:"+value);
                            if(value == 100){
                                progressButton.normal(2,false);
                                tv.setText("state normal");
                            }
                            progressButton.download(value);
                        }
                    });
                    valueAnimator.start();
                }else{
                    valueAnimator.cancel();
                    progressButton.normal(0,false);
                    tv.setText("state normal");
                }

            }
        });
    }

    private void button1() {
        final CProgressButton progressButton = (CProgressButton)findViewById(R.id.btn);

        final TextView tv = (TextView) findViewById(R.id.state);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
                if(progressButton.getState() == CProgressButton.STATE.NORMAL){
                    progressButton.startDownLoad();
                    valueAnimator.setDuration(5000);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int value = (int)animation.getAnimatedValue();
                            tv.setText("state progress:"+value);
                            if(value == 100){
                                progressButton.normal(2);
                                tv.setText("state normal");
                            }
                            progressButton.download(value);
                        }
                    });
                    valueAnimator.start();
                }else{
                    valueAnimator.cancel();
                    progressButton.normal(4);
                    tv.setText("state normal");
                }

            }
        });
    }
}
