package com.example.ratha.homework07_upload_iamge.repository.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ratha on 11/30/2017.
 */

public class ServiceGenerator {
    private static final String BASE_URL="http://110.74.194.125:15000/";
    private static OkHttpClient.Builder httpClient=new OkHttpClient.Builder();
    private static Retrofit.Builder builder=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S CreateService(Class<S> service){
        Retrofit retrofit=builder
                .client(httpClient.build())
                .build();
        return  retrofit.create(service);
    }
}
