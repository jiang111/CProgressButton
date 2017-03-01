# CProgressButton

a circle progress button like iOS <br />


![art](https://raw.githubusercontent.com/jiang111/CProgressButton/master/art/art2.gif)

## Usage:

###code
```
 final CProgressButton progressButton = (CProgressButton)findViewById(R.id.btn);
        progressButton.setbgDrawable(R.drawable.bounder,40); //config  bg
        progressButton.setStroke(1,R.color.colorAccent);  //config stroke color
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
```
###layout
```
<com.jiang.android.cprogressbutton.CProgressButton
        android:layout_width="65dp"
        android:layout_marginLeft="50dp"
        android:text="Download"
        android:gravity="center"
        android:textSize="12sp"
        android:textColor="@color/colorAccent"
        android:id="@+id/btn"
        android:layout_height="30dp" />
```

### License

    Copyright 2016 NewTab

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
