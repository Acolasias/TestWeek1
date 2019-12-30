package com.bawei.lqy.presenter;

import com.bawei.lqy.base.BasePresenter;
import com.bawei.lqy.contract.IMainContract;
import com.bawei.lqy.model.IMainModel;
import com.bawei.lqy.model.bean.GsonBean;

/**
 * Time:2019/12/30 0030下午 02:08201912
 * 邮箱:2094158527@qq.com
 * 作者:李庆瑶
 * 类功能:
 */
public class IMainPresenter extends BasePresenter<IMainContract.IView> implements IMainContract.IPresenter {

    private IMainModel iMainModel;

    @Override
    protected void initModel() {
        iMainModel = new IMainModel();
    }

    @Override
    public void onMainData() {
        iMainModel.onMainData(new IMainContract.IModel.IModelCalllback() {
            @Override
            public void onMainSueccess(GsonBean bean) {
                view.onMainSueccess(bean);
            }

            @Override
            public void onMainFailure(Throwable throwable) {
                view.onMainFailure(throwable);
            }
        });
    }
}
