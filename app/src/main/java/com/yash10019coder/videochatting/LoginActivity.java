package com.yash10019coder.videochatting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yash10019coder.videochatting.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}