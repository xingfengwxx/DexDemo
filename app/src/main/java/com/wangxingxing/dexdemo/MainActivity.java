package com.wangxingxing.dexdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.wangxingxing.dexdemo.dynamic.Dynamic;

import java.io.File;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    public static final String DEX_NAME = "dynamic_dex.jar";

    private Dynamic dynamic;
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = findViewById(R.id.tv_content);
        loadDex();
    }

    private void loadDex() {
        String destFilePath = PathUtils.getInternalAppCachePath() + File.separator + DEX_NAME;
        boolean state = ResourceUtils.copyFileFromAssets(DEX_NAME, destFilePath);
        LogUtils.i("copy file state=" + state);
        if (state) {
            DexClassLoader dexClassLoader = new DexClassLoader(destFilePath,
                    PathUtils.getInternalAppCachePath(),
                    null,
                    getClassLoader());
            try {
                Class clazz = dexClassLoader.loadClass("com.wangxingxing.dexdemo.dynamic.impl.DynamicImpl");
                dynamic = (Dynamic) clazz.newInstance();
                if (dynamic != null) {
                    tvContent.setText(dynamic.sayHello());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
