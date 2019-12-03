package com.parkingkoi.service.toolbox.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToolBoxRetrofitService {

    public static String baseUrl="http://parkingkoi.xyz/laravel_api/public/";
    private static Retrofit retrofit;


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}


   /* public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }*/
    //GitHubService service = retrofit.create(GitHubService.class);



