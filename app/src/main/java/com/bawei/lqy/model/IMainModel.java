package com.bawei.lqy.model;

import com.bawei.lqy.contract.IMainContract;
import com.bawei.lqy.model.bean.GsonBean;
import com.bawei.lqy.utile.NetUtile;
import com.google.gson.Gson;

/**
 * Time:2019/12/30 0030下午 02:06201912
 * 邮箱:2094158527@qq.com
 * 作者:李庆瑶
 * 类功能:
 */
public class IMainModel implements IMainContract.IModel {
    @Override
    public void onMainData(IModelCalllback iModelCalllback) {
        NetUtile.getInstance().onJsonGet("http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page=1&count=10&keyword=手机", new NetUtile.MyCallback() {
            @Override
            public void onGetJson(String json) {
                GsonBean gsonBean = new Gson().fromJson(json, GsonBean.class);
                iModelCalllback.onMainSueccess(gsonBean);
            }

            @Override
            public void onError(Throwable throwable) {
                iModelCalllback.onMainFailure(throwable);

            }
        });
    }
}
