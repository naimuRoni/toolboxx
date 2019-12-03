package com.parkingkoi.service.toolbox.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.parkingkoi.service.toolbox.R;
import com.parkingkoi.service.toolbox.databinding.FragmentPhonvarificationBinding;
import com.parkingkoi.service.toolbox.model.OTP;
import com.parkingkoi.service.toolbox.model.OtpRepository;
import com.parkingkoi.service.toolbox.view.otp.FragmentOtpSender;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class OtpViewModel extends AndroidViewModel {
    Context context;

    public String edotpcode,edotpNum;
    public Activity a;
    OtpRepository otpRepository;
    String getTypedOtpCode;
    public String verificationCode;
    public String otpone,otptwo,otpthree,otpfour,otpfive,otpsix;


    public MutableLiveData<String> numberSentliveData = new MutableLiveData<>();
    public MutableLiveData<Integer> progress = new MutableLiveData<>(8);
    public MutableLiveData<String> smsLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> varificationDone = new MutableLiveData<>();


    public OtpViewModel(Application application) {
        super(application);

    }

    public void otpTask(View view){
        otpRepository=new OtpRepository(a, edotpcode, edotpNum, new OtpRepository.trueListener() {
            @Override
            public void getVerificationID(String verificationId) {
                if(otpRepository.codeSent){
                    progress.setValue(8);
                    numberSentliveData.setValue(verificationId); }
                else {
                    progress.setValue(0); }
            }


        });


        progress.setValue(0);
        FirebaseApp.initializeApp(getApplication());
        otpRepository.OtpCall();






    }

    public void otpVarificationTask(View view){
        getTypedOtpCode=otpone+otptwo+otpthree+otpfour+otpfive+otpsix;
        Log.d("number",getTypedOtpCode);
        FirebaseApp.initializeApp(getApplication());
        otpRepository=new OtpRepository(a, new OtpRepository.isComplateOtp() {
            @Override
            public void isit(Boolean b) {
                if(b){
                    Toast.makeText(a,"varification Complate",Toast.LENGTH_SHORT).show();
                    varificationDone.setValue(b);
                }
                else {
                    Toast.makeText(a,"varification Not Complate Please Try Again",Toast.LENGTH_SHORT).show();
                }
            }
        });

         try{
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, getTypedOtpCode);

            otpRepository.OtpVerification(credential); }
         catch (Exception e){
         Toast toast = Toast.makeText(a,String.valueOf(e), Toast.LENGTH_SHORT);
         toast.show();
        }

    }

    public void setActivity(FragmentActivity activity) {
        this.a=activity;
    }

    public MutableLiveData<String> getConframation() {
        if (numberSentliveData == null) {
            numberSentliveData = new MutableLiveData<>(); }
        return numberSentliveData;
    }

    public MutableLiveData<Boolean> getSmsVerification() {
        if (varificationDone == null) {
            varificationDone = new MutableLiveData<>(); }
        return varificationDone;
    }

    public void setCode(String verificationCode){
        this.verificationCode=verificationCode;
    };



}
