package com.parkingkoi.service.toolbox.view.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.firebase.FirebaseApp;
import com.parkingkoi.service.toolbox.R;
import com.parkingkoi.service.toolbox.databinding.FragmentLoginBinding;
import com.parkingkoi.service.toolbox.view.otp.FragmentOtpSender;
import com.parkingkoi.service.toolbox.viewmodel.AuthViewModel;
import com.parkingkoi.service.toolbox.viewmodel.OtpViewModel;

public class FragmentLogin extends Fragment {

    FragmentLoginBinding binding;
    View view;
    AuthViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_login , container, false);
        FirebaseApp.initializeApp(getContext());

        view = binding.getRoot();
        viewModel = ViewModelProviders.of(FragmentLogin.this).get(AuthViewModel.class);
        viewModel.setActivity(getActivity());
        binding.setAuthViewModel(viewModel);
        binding.setLifecycleOwner(this);

        //impliment button task
        viewModel.getButtonTask().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer i) {
                if(i==0){
                    Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentRegistration);
                }
            }
        });
        return  view;
    }
}
