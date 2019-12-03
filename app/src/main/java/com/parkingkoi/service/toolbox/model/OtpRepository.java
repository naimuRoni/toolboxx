package com.parkingkoi.service.toolbox.model;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.parkingkoi.service.toolbox.MainActivity;

import java.util.concurrent.TimeUnit;

public class OtpRepository {
    FragmentActivity activity;
    String number;
    String verificationId;
    String smsCode;
    public Boolean codeSent=false;
    trueListener listener;
    isComplateOtp getCodelistner;
    FirebaseAuth auth;



    public OtpRepository(Activity a, String edotpcode, String edotpNum, trueListener listener) {
        this.activity = (FragmentActivity) a;
        this.number = edotpNum;
        this.listener = listener;






    }
    public OtpRepository(Activity a,isComplateOtp listner){
        this.getCodelistner=listner;

    }



    public void OtpCall() {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                activity,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        smsCode=phoneAuthCredential.getSmsCode();

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        String a=String.valueOf(e);
                        Toast.makeText(activity, a,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationId=s;
                        codeSent=true;
                        listener.getVerificationID(verificationId);
                        Log.d("log","true");


                    }
                });

    }
    public void OtpVerification(PhoneAuthCredential credential){
        auth = FirebaseAuth.getInstance();
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getCodelistner.isit(true);
                        } else {
                            Log.d("log", String.valueOf(task.getException()));
                            task.getException();
                        }
                    }

                });
    }



    //code by pappu
    public interface trueListener{
        public void getVerificationID(String verificationId);
    }
    public interface isComplateOtp{
        public void isit(Boolean b);
    }
}





