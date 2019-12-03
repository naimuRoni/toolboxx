package com.parkingkoi.service.toolbox.network;
import com.parkingkoi.service.toolbox.model.account.User;
import com.parkingkoi.service.toolbox.model.numbervalidate.NumValidation;
import com.parkingkoi.service.toolbox.model.ResponseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ToolBoxAPI {


    //is user number exist in toolbox server ?
     @POST("shop-owner-mobile")
     Call<List<ResponseUser>> getUserNumberValidation(@Body NumValidation body);

     //create user with phone number
     @POST("create-shop")
     Call<List<ResponseUser>> createAccount(@Body User body);


}
