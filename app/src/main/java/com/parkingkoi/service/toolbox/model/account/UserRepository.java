package com.parkingkoi.service.toolbox.model.account;


import android.util.Log;

import com.parkingkoi.service.toolbox.model.ResponseUser;
import com.parkingkoi.service.toolbox.model.numbervalidate.NumValidation;
import com.parkingkoi.service.toolbox.network.ToolBoxAPI;
import com.parkingkoi.service.toolbox.network.ToolBoxRetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    public static UserRepository userRepository;
    List<ResponseUser> reponseList=new ArrayList<>();
    private ToolBoxAPI toolBoxAPI;
    ResponseUser responseNumValidation;
    public isComplatetask listner;


    public static UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }
    public UserRepository(isComplatetask listner) {
        toolBoxAPI = ToolBoxRetrofitService.getRetrofitInstance().create(ToolBoxAPI.class);
        this.listner=listner;
    }
    public UserRepository() {
        toolBoxAPI = ToolBoxRetrofitService.getRetrofitInstance().create(ToolBoxAPI.class);
    }

    public void createUser(User user){

        toolBoxAPI.createAccount(user).enqueue(new Callback<List<ResponseUser>>() {
            @Override
            public void onResponse(Call<List<ResponseUser>> call, Response<List<ResponseUser>> response) {
                if (response.isSuccessful()){
                    reponseList=response.body();
                    responseNumValidation=reponseList.get(0);
                    Log.d("job2",responseNumValidation.getMsg());
                    listner.serverResponse(responseNumValidation);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseUser>> call, Throwable t) {
                Log.d("network", String.valueOf(t));
            }
        });

    }

    public interface isComplatetask{
        public void serverResponse(ResponseUser response);
    }
}
