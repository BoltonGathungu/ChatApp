package com.example.chatapp2.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.chatapp2.R;
import com.example.chatapp2.databinding.ActivityLoginBinding;
import com.example.chatapp2.viewmodel.MyViewModel;

public class LoginActivity extends AppCompatActivity {

    MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //use ViewModelProvider to initialize the viewModel
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        ActivityLoginBinding activityLoginBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);

        activityLoginBinding.setVModel(viewModel);
    }
}