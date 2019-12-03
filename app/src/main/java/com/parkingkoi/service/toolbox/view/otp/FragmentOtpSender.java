package com.parkingkoi.service.toolbox.view.otp;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.PhoneAuthProvider;
import com.parkingkoi.service.toolbox.R;
import com.parkingkoi.service.toolbox.databinding.FragmentPhonvarificationBinding;
import com.parkingkoi.service.toolbox.model.OTP;
import com.parkingkoi.service.toolbox.viewmodel.OtpViewModel;

import java.util.concurrent.TimeUnit;

public class FragmentOtpSender extends Fragment {
FragmentPhonvarificationBinding binding;
OtpViewModel otpViewModel;
    View view;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       binding=DataBindingUtil.inflate(inflater, R.layout.fragment_phonvarification , container, false);
       FirebaseApp.initializeApp(getContext());

        view = binding.getRoot();
        otpViewModel = ViewModelProviders.of(FragmentOtpSender.this).get(OtpViewModel.class);
        otpViewModel.setActivity(getActivity());
        binding.setViewmodel(otpViewModel);
        binding.setLifecycleOwner(this);



        otpViewModel.getConframation().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String smsCode) {
                if(!smsCode.equals("")){

                    FragmentOtpSenderDirections.ActionFragmentOtpSenderToFragmentOtpReciver action= FragmentOtpSenderDirections.actionFragmentOtpSenderToFragmentOtpReciver();
                    action.setUserNumber(otpViewModel.edotpNum);
                    action.setVarificationId(smsCode);
                    Navigation.findNavController(view).navigate(action);
                }

            }
        });







        return view;
    }



}
