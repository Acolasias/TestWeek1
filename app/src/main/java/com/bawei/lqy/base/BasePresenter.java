package com.bawei.lqy.base;

/**
 * Time:2019/12/30 0030下午 01:48201912
 * 邮箱:2094158527@qq.com
 * 作者:李庆瑶
 * 类功能:
 */
public abstract class BasePresenter<V> {
    protected V view;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void detach() {
        view=null;
    }

    public void attach(V view) {
        this.view = view;
    }
}
