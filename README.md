# CProgressButton

a circle progress button like iOS <br />


![art](https://raw.githubusercontent.com/jiang111/CProgressButton/master/art/art2.gif)

## Usage:

###code
```
//config in your app
CProgressButton.initStatusString(new String[]{"download","pause","complete","error","delete"});
 //use 
CProgressButton progressButton = (CProgressButton)findViewById(R.id.btn2);
progressButton.normal(0/1/2/3); //mac value is the String[] length - 1;
progressButton.startDownload();
progressButton.download(progress);
```
###layout
```
 <com.jiang.android.cprogressbutton.CProgressButton
            android:layout_width="65dp"
            android:layout_marginLeft="50dp"
            android:gravity="center"
            app:stroke_width="1dp"  //stroke outside width
            app:radius="40dp"       //outside  radius
            app:color="@color/colorAccent"       //all line color
            app:drawable_xml="@drawable/bounder"  //bg drawable
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
