package com.ecommerceapp.data.source.remote;

import com.ecommerceapp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    public static Retrofit createRetrofitInstance() {

        if (retrofit == null)
        {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.addInterceptor(interceptor);

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://52.25.13.115:7000/interview/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(client.build())
                    .build();
        }
        return retrofit;
    }


    public static NetworkApi getNetworkClass() {
        return retrofit.create(NetworkApi.class);
    }

}
