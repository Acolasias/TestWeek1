package com.bawei.lqy.contract;

import com.bawei.lqy.model.bean.GsonBean;

/**
 * Time:2019/12/30 0030下午 02:04201912
 * 邮箱:2094158527@qq.com
 * 作者:李庆瑶
 * 类功能:
 */
public interface IMainContract {
    interface IView{
        void onMainSueccess(GsonBean bean);
        void onMainFailure(Throwable throwable);
    }

    interface IPresenter{
        void onMainData();
    }

    interface IModel{
        void onMainData(IModelCalllback iModelCalllback);
        interface IModelCalllback{
            void onMainSueccess(GsonBean bean);
            void onMainFailure(Throwable throwable);
        }
    }
}
