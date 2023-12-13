package com.example.cinema_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth=FirebaseAuth.getInstance();
        sendData();
        loginText();
    }

    public void sendData(){
        Button register = (Button) findViewById(R.id.signUp_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.email);
                EditText password = findViewById(R.id.password);
                EditText rePassword = findViewById(R.id.reEnterPassword);

                String email_text= email.getText().toString();
                String password_text= password.getText().toString();
                String rePassword_text= rePassword.getText().toString();

                if(TextUtils.isEmpty(email_text))
                {
                    Toast.makeText(getApplicationContext(),"Enter your e-mail address",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password_text))
                {
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(rePassword_text))
                {
                    Toast.makeText(SignUp.this,"Re-enter your password",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email_text, password_text)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUp.this, "Account created successfully.",
                                            Toast.LENGTH_SHORT).show();
                                    /*
                                     Intent intent = new
                                           Intent(SignUp.this,Home.class);
                                           startActivity(intent);

                                    **/

                                } else {
                                    Toast.makeText(SignUp.this, "Failed to create account,try again.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
    }
    public void loginText(){
        TextView login = (TextView) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new
                        Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
    }


}