package com.parkingkoi.service.toolbox.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.parkingkoi.service.toolbox.model.account.User;
import com.parkingkoi.service.toolbox.model.account.UserRepository;
import com.parkingkoi.service.toolbox.model.numbervalidate.NumValidation;
import com.parkingkoi.service.toolbox.model.numbervalidate.NumverValidationRepository;
import com.parkingkoi.service.toolbox.model.ResponseUser;
import com.parkingkoi.service.toolbox.view.auth.FragmentRegistration;

public class AuthViewModel extends AndroidViewModel {
    Activity a;
    User user;
    public String phoneNumber="",password="",repeatPass="";
    MutableLiveData<Integer>buttonPress=new MutableLiveData<>();
    MutableLiveData<String>getBackendMessage=new MutableLiveData<>();


    private MutableLiveData<ResponseUser> numValidationMutableLiveData;
    private NumverValidationRepository repository;
    private UserRepository userRepository;
    private NumValidation number;

    public AuthViewModel(@NonNull Application application) {
        super(application);
    }

    public void onTapSigninTask(View view){
        Toast.makeText(a,"tap signin",Toast.LENGTH_SHORT).show();
    }
    public void onTapSignUPTask(View view){
        Toast.makeText(a,phoneNumber,Toast.LENGTH_SHORT).show();


        singUpfromValidation();

        if(singUpfromValidation()){
            number=new NumValidation(phoneNumber);
            number.setPostMobile(phoneNumber);
          repository = NumverValidationRepository.getInstance();
          repository= new NumverValidationRepository(new NumverValidationRepository.isComplatetask() {
              @Override
              public void serverResponse(ResponseUser response) {
                  numValidationMutableLiveData.setValue(response);
                  Log.d("msg",response.getMsg());
              }
          });
          repository.getUserNumberValidation(number);


        }
    }

    private Boolean singUpfromValidation() {
        Boolean result=false;
        if(phoneNumber.equals("")){
            getBackendMessage.setValue("Please enter the number");
        }
        else if(phoneNumber.length()!=11){
            getBackendMessage.setValue("Please enter the number properly");
        }
        else if(!password.equals(repeatPass)){
            getBackendMessage.setValue("Please use the same password");
        }
        else result=true;
        FragmentRegistration registration=new FragmentRegistration();
        registration.getUserFromViewModel(user=new User(phoneNumber,password));
        return result;
    }

    public void onTapSignUp(View view){
        buttonPress.setValue(0);
        Toast.makeText(a,"tap signup",Toast.LENGTH_SHORT).show();
    }
    public void onTapSignin(View view){

    }
    public void onTapForgetPass(View view){

    }

    public MutableLiveData<Integer> getButtonTask() {
        if (buttonPress == null) {
            buttonPress = new MutableLiveData<>(); }
        return buttonPress;
    }
    public MutableLiveData<String> getErrorMessage() {
        if (getBackendMessage == null) {
            getBackendMessage = new MutableLiveData<>(); }
        return getBackendMessage;
    }
    public MutableLiveData<ResponseUser> getBackendMessage() {
        Log.d("do","do");
        if (numValidationMutableLiveData == null) {
            numValidationMutableLiveData = new MutableLiveData<>(); }
        return numValidationMutableLiveData;
    }

    public void registeredAccount(){
         User newuser=new User(phoneNumber,password);
        userRepository = UserRepository.getInstance();
        userRepository= new UserRepository(new UserRepository.isComplatetask() {
            @Override
            public void serverResponse(ResponseUser response) {
                numValidationMutableLiveData.setValue(response);
                Log.d("msg",response.getMsg());
            }
        });
        userRepository.createUser(newuser);
        Log.d("user",newuser.getUserNumber());
    }


    public void setActivity(FragmentActivity activity) {
        this.a=activity;
    }
}
