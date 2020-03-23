package com.vikas.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    String TAG="RegisterActivity";
    private EditText etEmail,etPassword;
    private Button bRegister;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews() {

            firebaseAuth= FirebaseAuth.getInstance();
            etPassword=findViewById(R.id.et_password_register);
            etEmail=findViewById(R.id.et_email_register);
            bRegister=findViewById(R.id.b_register_successfully);
            bRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerClick();
                }
            });

    }
    private void registerClick() {
        Log.d(TAG, "registerClick: ");
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        if(etEmail.getText().toString().equals(""))
        {
            etEmail.setError("Enter Your Email-id");
        }
        else if(etPassword.getText().toString().equals("") )
        {
            etPassword.setError("Enter your Password");
        }
        else if(etPassword.length()<6)
        {
            Toast.makeText(getApplicationContext(),"Password must be at least 6 characters",Toast.LENGTH_SHORT).show();
        }
        else
        {
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(RegisterActivity.this, "Register Successfully",
                                Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
//                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
//                        updateUI(null);
                    }

                }
            });
        }
    }
}
