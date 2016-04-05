# Android CatLoadingView

This project idea is from [Link](http://mp.weixin.qq.com/s?__biz=MjM5MDMxOTE5NA==&mid=402703079&idx=2&sn=2fcc6746a866dcc003c68ead9b68e595&scene=2&srcid=0302A7p723KK8E5gSzLKb2ZL&from=timeline&isappinstalled=0#wechat_redirect) .<br>
Thanks for the idea.<br>

I like the animation in that picture.<br>

![](http://ww4.sinaimg.cn/mw690/a695acdejw1f2hbmfxzk5g208u0a2ass.gif)

..as you see right now,wish you like it.

### Step

Import this project into android studio..it's build with it.

###  Usage

#### Gradle

```
	compile project(':library')
```

####  config in java code

    CatLoadingView mView;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mView = new CatLoadingView();
        findViewById(R.id.button).setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        mView.show(getSupportFragmentManager(), "");
                    }
                });
    }

## TODO

This view is adjust in Nexue5 but not test in the other size screen .

## About me

A small guy  in mainland FUJIAN China.

If you have any new idea about this project, feel free to tell me ,Tks. :smiley:


## License

	The MIT License (MIT)

	Copyright © 2015 Roger

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CatLoadingView-green.svg?style=true)](https://android-arsenal.com/details/1/3369)
