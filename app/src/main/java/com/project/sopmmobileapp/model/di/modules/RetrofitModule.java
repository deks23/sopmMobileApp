package com.project.sopmmobileapp.model.di.modules;


import com.project.sopmmobileapp.model.store.TokenStore;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    private static final String BASE_URL = "https://sopmr-webservices.azurewebsites.net";

    @Provides
    @Singleton
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Named("auth")
    @Singleton
    @Provides
    public OkHttpClient authClient(HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(loggingInterceptor);
        builder.addInterceptor(chain -> {
            okhttp3.Request request = chain.request();
            String authToken = TokenStore.getToken();
            Headers headers = request.headers().newBuilder().
                    add("Authorization", "Bearer " + authToken)
                    .build();
            request = request.newBuilder().headers(headers).build();
            return chain.proceed(request);
        });
        return builder.build();
    }

    @Named("no_auth")
    @Singleton
    @Provides
    public OkHttpClient client(HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    @Named("no_auth")
    @Singleton
    @Provides
    public Retrofit retrofit(@Named("no_auth") OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }
}
