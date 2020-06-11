package com.turtlesoftsolution.turtlevideoconference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    ProgressBar pbar;
    Button LoginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        LoginButton = findViewById(R.id.LoginButton);
        pbar = findViewById(R.id.pbar);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = editTextEmail.getText().toString().trim();
                String Pass = editTextPassword.getText().toString().trim();
                pbar.setVisibility(View.VISIBLE);
                if (Pass.length() < 6) {
                    pbar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Password Must be Greater then 6 ", Toast.LENGTH_SHORT).show();
                } else if (Email.isEmpty()) {
                    pbar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(Email, Pass)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        pbar.setVisibility(View.GONE);
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                    } else {
                                        pbar.setVisibility(View.GONE);
                                        Toast.makeText(LoginActivity.this, "Please Enter Valid Email And Password", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });
    }

    public void goToRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(intent);
    }

    @Override

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

}
