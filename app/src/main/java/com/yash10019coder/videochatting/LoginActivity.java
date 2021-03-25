package com.yash10019coder.videochatting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.yash10019coder.videochatting.databinding.ActivityLoginBinding;

import static java.lang.Character.isDigit;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String email=binding.etEmail.getText().toString().trim();
        String password=binding.etPassword.getText().toString().trim();
        auth=FirebaseAuth.getInstance();
        binding.btsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUp.class));
            }
        });

    }
}