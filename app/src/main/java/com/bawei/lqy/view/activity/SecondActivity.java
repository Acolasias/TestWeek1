package com.bawei.lqy.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.lqy.R;
import com.bawei.lqy.base.BaseActivity;
import com.bawei.lqy.base.BasePresenter;
import com.bawei.lqy.model.bean.Bean;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {

    @BindView(R.id.edtest)
    EditText edtest;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CodeUtils.analyzeByImageView(img, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(SecondActivity.this, "扫描成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(SecondActivity.this, "扫描失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    protected BasePresenter providerPresenter() {
        return null;
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_second;
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                String string = edtest.getText().toString();
                Bitmap image = CodeUtils.createImage(string, 600, 600, null);
                img.setImageBitmap(image);
                break;
            case R.id.button2:
                EventBus.getDefault().post("字符串");
                break;
            case R.id.button3:
                EventBus.getDefault().post(new Bean("李庆瑶","Man"));
                break;
        }
    }
    @Subscribe
    public void onGetString(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetString(Bean bean){
        Toast.makeText(this, bean.getName()+"&"+bean.getSex(), Toast.LENGTH_SHORT).show();
    }
}
