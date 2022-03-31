package com.example.menutest.network.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.menutest.common.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    public static Retrofit client(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("application", "mobile-application")
                        .header("Content-Type", "application/json")
                        .header("Device-UUID", "123456")
                        .header("Api-Version", "3.7.0")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)

                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(builder.build())
                .build();
    }
}
