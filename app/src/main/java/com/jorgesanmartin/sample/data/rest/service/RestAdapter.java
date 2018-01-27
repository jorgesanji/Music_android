package com.jorgesanmartin.sample.data.rest.service;

import com.jorgesanmartin.sample.data.utils.RetrofitUtils;
import com.jorgesanmartin.sample.data.utils.RxErrorHandlingCallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by jorgesanmartin on 11/2/17.
 */

public class RestAdapter {

    public static final long CONNECT_TIME_OUT = 60;
    public static final long READ_TIME_OUT = 60;

    private RestApiService restAPIService;
    private OkHttpClient okHttpClient;

    public RestAdapter() {
        this.restAPIService = generateApiService();
    }

    private RestApiService generateApiService() {
        okHttpClient = generateOkHttpClient().build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .baseUrl(RestConstants.BaseUrl)
                .addConverterFactory(RetrofitUtils.buildGSONConverter())
                .client(okHttpClient)
                .build();
        return retrofit.create(RestApiService.class);
    }

    private OkHttpClient.Builder generateOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        httpClient.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        httpClient.addInterceptor(interceptor);
        httpClient.addInterceptor(generateAuthInterceptor());
        httpClient.retryOnConnectionFailure(true);
        return httpClient;
    }

    private Request buildRequest(Request original) {
        Request.Builder builder = original.newBuilder()
                .method(original.method(), original.body());
        return builder.build();
    }

    private Interceptor generateAuthInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain)
                    throws IOException {
                return chain.proceed(buildRequest(chain.request()));
            }
        };
    }

    public RestApiService getService() {
        return restAPIService;
    }
}