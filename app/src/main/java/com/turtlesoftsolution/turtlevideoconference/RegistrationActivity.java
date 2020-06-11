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

public class RegistrationActivity extends AppCompatActivity {
EditText editTextName,editTextenrollment,editTextEmail,editTextPassword;
Button SignupButton;
ProgressBar pbar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editTextName = findViewById(R.id.editTextName);
        editTextenrollment = findViewById(R.id.editTextenrollment);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        SignupButton = findViewById(R.id.SignupButton);
        mAuth = FirebaseAuth.getInstance();
        pbar = findViewById(R.id.pbar);
        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String Enroll = editTextenrollment.getText().toString();
                String Pass = editTextPassword.getText().toString().trim();
                String Email = editTextEmail.getText().toString().trim();
                pbar.setVisibility(View.VISIBLE);

                if(name.isEmpty())
                {
                    Toast.makeText(RegistrationActivity.this, "Please Enter your Name", Toast.LENGTH_SHORT).show();
                pbar.setVisibility(View.GONE);
                }
                else if(Enroll.length() != 12)
                {
                    Toast.makeText(RegistrationActivity.this, "Enrollment Number Must be 12 digits", Toast.LENGTH_SHORT).show();
                    pbar.setVisibility(View.GONE);
                }
                else if(Pass.toString().length() < 6)
                {

                    Toast.makeText(RegistrationActivity.this, "Password Must be Greater then 6 ", Toast.LENGTH_SHORT).show();
                    pbar.setVisibility(View.GONE);
                }

                else if(Email.isEmpty())
                {
                    Toast.makeText(RegistrationActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    pbar.setVisibility(View.GONE);
                }
                else{
                mAuth.createUserWithEmailAndPassword(Email, Pass)
                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(RegistrationActivity.this, "You Have Registered Successfully", Toast.LENGTH_SHORT).show();
                                    pbar.setVisibility(View.GONE);
                                } else {
                                    Toast.makeText(RegistrationActivity.this, "This Email Is Already Exist", Toast.LENGTH_SHORT).show();
                                    pbar.setVisibility(View.GONE);
                                }

                            }
                        });

            }}
        });
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }
}
