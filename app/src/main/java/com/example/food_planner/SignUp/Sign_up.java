package com.example.food_planner.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.food_planner.Login.Login;
import com.example.food_planner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_up extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText signup_Email;
    private EditText signup_Password;
    private Button signup_Button;
    private TextView loginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        signup_Email = findViewById(R.id.signup_email);
        signup_Password = findViewById(R.id.signup_password);
        signup_Button = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signup_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = signup_Email.getText().toString().trim();
                String password = signup_Password.getText().toString().trim();
                if(user.isEmpty())
                {
                    signup_Email.setError("Email cannot be empty");
                }
                if (password.isEmpty()){
                    signup_Password.setError("Password cannot be empty");
                } else{
                    auth.createUserWithEmailAndPassword(user, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Sign_up.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Sign_up.this, Login.class));
                            } else {
                                Toast.makeText(Sign_up.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sign_up.this, Login.class));
            }
        });
    }
}