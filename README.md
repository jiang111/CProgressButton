# CProgressButton

a circle progress button like iOS <br />
[![](https://jitpack.io/v/jiang111/CProgressButton.svg)](https://jitpack.io/#jiang111/CProgressButton) <br />

![art](https://raw.githubusercontent.com/jiang111/CProgressButton/master/art/art2.gif)

## Usage:

### Gradle
```
Add it in your root build.gradle at the end of repositories:
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.jiang111:CProgressButton:v1.0'
	}
```


### code
```
//config in your app
CProgressButton.initStatusString(new String[]{"download","pause","complete","error","delete"});
 //use 
CProgressButton progressButton = (CProgressButton)findViewById(R.id.btn2);
if(progressButton.getState() != CProgressButton.STATE.NORMAL){
	progressButton.normal(0/1/2/3); //max value is String[].length - 1;  call anytime;
}else{
	progressButton.startDownload();  //you must call startDownload() before download(progress);
	progressButton.download(progress);
}
```
### xml

layout
```
 <com.jiang.android.pbutton.CProgressButton
            android:layout_width="65dp"
            android:layout_marginLeft="50dp"
            android:gravity="center"
            app:stroke_width="1dp"  //stroke outside width -> the width in bounder.xml
            app:radius="40dp"       //outside  radius  -> the radius in bounder.xml
            app:color="@color/colorAccent"       //all line color the color in bounder.xml
            app:drawable_xml="@drawable/bounder"  //bg drawable
            android:textSize="12sp"
            android:textColor="@color/colorAccent"
            android:id="@+id/btn"
            android:layout_height="30dp" />
```
drawable-> bounder.xml
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android" android:shape="rectangle">
    <stroke android:color="@color/colorAccent" android:width="1dp" />
    <corners android:radius="40dp" />
</shape>
```

### Other
 If you found this library helpful or you learned something today and want to thank me, [buying me a cup of ☕️  with paypal](https://www.paypal.me/jyuesong) <br /><br />
![](https://raw.githubusercontent.com/jiang111/RxJavaApp/master/qrcode/wechat_alipay.png)


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
