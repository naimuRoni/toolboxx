package com.parkingkoi.service.toolbox.view.otp;

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
import com.parkingkoi.service.toolbox.R;
import com.parkingkoi.service.toolbox.databinding.FragmentOtpReciverBinding;
import com.parkingkoi.service.toolbox.viewmodel.OtpViewModel;

public class FragmentOtpReciver extends Fragment {
    FragmentOtpReciverBinding binding;
    OtpViewModel otpViewModel;
    View view;
    private String number;
    String varificationId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp_reciver, container, false);
        FirebaseApp.initializeApp(getContext());

        view = binding.getRoot();
        otpViewModel = ViewModelProviders.of(FragmentOtpReciver.this).get(OtpViewModel.class);
        otpViewModel.setActivity(getActivity());
        binding.setViewmodel(otpViewModel);
        binding.setLifecycleOwner(this);

        //get sms code
        getCode();
        otpViewModel.setCode(varificationId);
        otpViewModel.getSmsVerification().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                FragmentOtpReciverDirections.ActionFragmentOtpReciverToFragmentLogin action = FragmentOtpReciverDirections.actionFragmentOtpReciverToFragmentLogin();
                action.setUserNumber(number);
                Navigation.findNavController(view).navigate(action);
            }
        });

        return view;
    }

    private void getCode() {
        if(getArguments()!=null){
            FragmentOtpReciverArgs args=FragmentOtpReciverArgs.fromBundle(getArguments());
            number=args.getUserNumber();
            varificationId=args.getVarificationId();
            binding.tvOtpnumber.setText(number);
        }
    }
}
