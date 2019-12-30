package com.bawei.lqy.base;

import android.os.Bundle;
import android.text.Layout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Time:2019/12/30 0030下午 01:50201912
 * 邮箱:2094158527@qq.com
 * 作者:李庆瑶
 * 类功能:
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutId());
        mPresenter=providerPresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P providerPresenter();

    protected abstract int LayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
