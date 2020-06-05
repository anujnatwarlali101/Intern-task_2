package com.example.studentscompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.LocusId;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username,email,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_emailid);
        password = findViewById(R.id.signup_password);
        findViewById(R.id.signup_button).setOnClickListener(this);
        findViewById(R.id.signup_go_back).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    private void registerUser()
    {
        String name = username.getText().toString().trim();
        String signupEmail = email.getText().toString().trim();
        String signupPassword = password.getText().toString().trim();

        if(name.isEmpty())
        {
            username.setError("This Field is required");
            username.requestFocus();
            return;
        }
        if(signupEmail.isEmpty())
        {
            email.setError("This field is required");
            email.requestFocus();
            return;
        }
        if(signupPassword.isEmpty())
        {
            password.setError("This field is required");
            password.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(signupEmail).matches())
        {
            email.setError("Enter a Valid Email-ID");
            email.requestFocus();
            return;
        }
        if(signupPassword.length()<6)
        {
            password.setError("Minimum Six characters required");
            password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(signupEmail,signupPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(RegisterActivity.this, "The Email-ID is already registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    }else
                    {
                        Toast.makeText(RegisterActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.signup_button:
                registerUser();
                break;

            case R.id.signup_go_back:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }
}
