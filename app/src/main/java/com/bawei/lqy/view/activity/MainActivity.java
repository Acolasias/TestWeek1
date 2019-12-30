package com.bawei.lqy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.lqy.R;
import com.bawei.lqy.base.BaseActivity;
import com.bawei.lqy.base.BasePresenter;
import com.bawei.lqy.contract.IMainContract;
import com.bawei.lqy.model.bean.GsonBean;
import com.bawei.lqy.presenter.IMainPresenter;
import com.bawei.lqy.view.adapter.MyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<IMainPresenter> implements IMainContract.IView {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void initData() {
        mPresenter.onMainData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected IMainPresenter providerPresenter() {
        return new IMainPresenter();
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onMainSueccess(GsonBean bean) {
        List<GsonBean.ResultBean> result = bean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter(result);
        myAdapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "条目"+position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        rv.setAdapter(myAdapter);
        Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMainFailure(Throwable throwable) {
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
        Log.i("xxx",throwable.getMessage());
    }
}
