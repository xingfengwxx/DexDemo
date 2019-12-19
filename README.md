# dex文件代码

package com.wangxingxing.dexdemo.dynamic.impl;

import com.wangxingxing.dexdemo.dynamic.Dynamic;

public class DynamicImpl implements Dynamic {

    private static final String TAG = "DynamicImpl";

    @Override
    public String sayHello() {
        return TAG + ": hello world!";
    }
}

# 参考地址
https://www.jianshu.com/p/ea81f7621229