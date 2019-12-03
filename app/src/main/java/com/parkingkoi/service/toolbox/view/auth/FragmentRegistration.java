package com.parkingkoi.service.toolbox.view.auth;

import android.os.Bundle;
import android.util.Log;
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
import com.parkingkoi.service.toolbox.databinding.FragmentRegistrationBinding;
import com.parkingkoi.service.toolbox.model.ResponseUser;
import com.parkingkoi.service.toolbox.model.account.User;
import com.parkingkoi.service.toolbox.viewmodel.AuthViewModel;

public class FragmentRegistration extends Fragment {
    FragmentRegistrationBinding binding;
    AuthViewModel viewModel;
    View view;
    User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_registration , container, false);
        FirebaseApp.initializeApp(getContext());

        view = binding.getRoot();
        viewModel = ViewModelProviders.of(FragmentRegistration.this).get(AuthViewModel.class);
        viewModel.setActivity(getActivity());
        binding.setAuthViewModel(viewModel);
        binding.setLifecycleOwner(this);

        //impliment message task
        viewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                tostMessage(message);
            }
        });
        //number validation
        viewModel.getBackendMessage().observe(this, new Observer<ResponseUser>() {
            @Override
            public void onChanged(ResponseUser response) {

                if(response.getCode()==409){
                    tostMessage(response.getMsg()); }
                else {

                    viewModel.registeredAccount();
                    Navigation.findNavController(view).navigate(R.id.action_fragmentRegistration_to_HOmeActiviy);
                }

            }

        });
        return  view;
    }

    public void tostMessage(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
    public void getUserFromViewModel(User user){
        this.user=user;
        Log.d("user",user.getUserNumber());
    }

}
