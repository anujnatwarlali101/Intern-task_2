package com.example.studentscompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.login_new_register).setOnClickListener(this);
        findViewById(R.id.login_forgot_pass).setOnClickListener(this);
        email = findViewById(R.id.login_emailid);
        password = findViewById(R.id.login_password);

        mAuth = FirebaseAuth.getInstance();
    }

    private void userLogin()
    {

        String loginEmail = email.getText().toString().trim();
        String loginPassword = password.getText().toString().trim();
        if(loginEmail.isEmpty())
        {
            email.setError("This field is required");
            email.requestFocus();
            return;
        }
        if(loginPassword.isEmpty())
        {
            password.setError("This field is required");
            password.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(loginEmail).matches())
        {
            email.setError("Enter a Valid Email-ID");
            email.requestFocus();
            return;
        }
        if(loginPassword.length()<6)
        {
            password.setError("Minimum Six characters required");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(loginEmail,loginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "User Logged In", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(LoginActivity.this,DashboardActivity.class);
                    startActivity(intent);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();

                }
                else
                {
                    Toast.makeText(LoginActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void forgotPassword()
    {
        String loginemail = email.getText().toString().trim();

        if(loginemail.isEmpty())
        {
            email.setError("This field is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(loginemail).matches())
        {
            email.setError("Enter a Valid Email-ID");
            email.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(loginemail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "Check your mail for Password Reset Link", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.login_new_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.login_button:
                userLogin();
                break;
            case R.id.login_forgot_pass:
                forgotPassword();
                break;
        }

    }
}
