package com.yash10019coder.videochatting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.yash10019coder.videochatting.databinding.ActivitySignUpBinding;
import com.yash10019coder.videochatting.models.users;

public class SignUp extends AppCompatActivity {
    ActivitySignUpBinding binding;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    String TAG = "TAG";
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate: signup");
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        progressDialog.setTitle("Logging in");
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
//            progressDialog.show();
            startActivity(new Intent(SignUp.this, DashboardActivity.class));
//            progressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        progressDialog.setTitle("Sign Up");
//        progressDialog.setMessage("Creating your Account");
        binding.btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, LoginActivity.class));
            }
        });
        binding.btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressDialog.show();
                String name = binding.etUsername.getText().toString().trim();
                String email = binding.etEmail.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();
                if( !email.equals("") && !password.equals("") && !name.equals("") ){
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
//                        progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                users users1 = new users();
                                users1.setUsername(binding.etUsername.getText().toString());
                                users1.setId(mAuth.getUid());
                                users1.setMail(email);
                                users1.setUser_password(password);
                                db.getReference("users").child(mAuth.getUid()).setValue(users1);
                                startActivity(new Intent(SignUp.this, DashboardActivity.class));
                                Toast.makeText(SignUp.this, "Your Account is created.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUp.this, task.getException().getLocalizedMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}