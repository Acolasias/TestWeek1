package com.bawei.lqy.utile;

import android.os.Handler;
import android.widget.ImageView;

import com.bawei.lqy.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Time:2019/12/30 0030下午 01:54201912
 * 邮箱:2094158527@qq.com
 * 作者:李庆瑶
 * 类功能:
 */
public class NetUtile {
    private static NetUtile netUtile;
    private  Handler handler;
    private  OkHttpClient okHttpClient;

    public NetUtile() {
        handler = new Handler();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor httpLoggingInterceptor1 = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor1)
                .build();
    }

    public static NetUtile getInstance() {
        if(netUtile==null){
            synchronized (NetUtile.class){
                if(netUtile==null){
                    netUtile=new NetUtile();
                }
            }
        }
        return netUtile;
    }
    public interface MyCallback{
        void onGetJson(String json);
        void onError(Throwable throwable);
    }

    public void onJsonGet(String httpUrl,MyCallback myCallback){
        Request request = new Request.Builder()
                .get()
                .url(httpUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallback.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.onGetJson(string);
                        }
                    });
                }
            }
        });
    }

    public void onJsonPost(String httpUrl, Map<String,String> map,MyCallback myCallback){
        FormBody.Builder builder = new FormBody.Builder();
        for (String key:map.keySet()){
            String value = map.get(key);
            builder.add(key,value);
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(httpUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallback.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.onGetJson(string);
                        }
                    });
                }
            }
        });
    }

    public void onPhoto(String httpUrl, ImageView imageView){
        Glide.with(imageView).load(httpUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(imageView);
    }
}
