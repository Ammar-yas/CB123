package com.intdv.cb123;

import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BasePresenter {

    private String mDevice = "";
    private Gson mGson;
    public String error;

    BasePresenter() {
        mGson = new Gson();
    }

    protected ApiService getRetrofit() {

            return new Retrofit.Builder()
                    .baseUrl("https://directline.botframework.com/v3/directline/")
                    .client(getRetrofitClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(ApiService.class);

    }

    <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);
    }

    private long timeoutDuration = 60;
    private OkHttpClient getRetrofitClient() {
        return new OkHttpClient.Builder()
                .readTimeout(timeoutDuration, TimeUnit.SECONDS)
                .connectTimeout(timeoutDuration, TimeUnit.SECONDS)
                .build();
    }

}
