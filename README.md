# Android MatchView

This project is learn from (https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh) .<br>
Thanks for liaohuqiu..<br>

I like the animation in that project...so i studied his codes and make this..<br>

![](http://ww1.sinaimg.cn/mw690/a695acdegw1emytnk4s45g20eg0mk490.gif)

..as you see right now,wish you like it.

## Demo

[Download Demo](https://raw.githubusercontent.com/Rogero0o/MatchView/master/demo/MatchView_Demo_V1.0.apk)

### Step

Import this project into android studio..it's build with it.

###  Usage

#### Maven Central

gradle
```
compile project(':library')
```

##### Config in xml

```xml
 xmlns:match="http://schemas.android.com/apk/res-auto"

<com.roger.match.library.MatchTextView
        match:text="MATCH VIEW"
        match:textSize="30sp"
        match:textColor="#ffffff"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
        
<com.roger.match.library.MatchButton
        match:text="Yes"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
```

##### Or config in java code

```java
// the following are default settings
 mMatchTextView.setText("MATCH VIEW");
 mMatchTextView.setTextSize(30);
 mMatchTextView.setTextColor(Color.WHITE);
// setProgress  float 0-1
 mMatchTextView.setProgress(0.5f);
```


## About me

A small guy  in mainland FUJIAN China.

If you have any new idea about this project, feel free to tell me ,Tks. :smiley:

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MatchView-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1194)
