package com.example.yigitcan.EventManager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Activitysignup extends AppCompatActivity implements OnCompleteListener
{
    private Button proceedBtn;
    private EditText nameTxt,surnameTxt,emailTxt,passTxt,phoneNoTxt,validatePassTxt;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        proceedBtn=(Button)findViewById(R.id.Signup);

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid())
                    RegisterUser();
            }
        });
        emailTxt=(EditText)findViewById(R.id.email);
        nameTxt=(EditText)findViewById(R.id.name);
        surnameTxt=(EditText)findViewById(R.id.surname);
        passTxt=(EditText)findViewById(R.id.password);
        phoneNoTxt=(EditText)findViewById(R.id.phone);
        validatePassTxt=(EditText)findViewById(R.id.passwordv);
    }
    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()) {
            Toast.makeText(getApplicationContext(), "Registration is completed", Toast.LENGTH_SHORT).show();
            mAuth.getCurrentUser().sendEmailVerification();
            startActivity(new Intent(Activitysignup.this,Activitylogin.class));
        } else {
            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                emailTxt.setError("A user with this email adress already exists, please enter another email adress");
                emailTxt.requestFocus();
            } else {
                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public boolean isValid()
    {
        if (this.nameTxt.getText().toString().matches("[a-zA-Z]{2,}") &&
                this.surnameTxt.getText().toString().matches("[a-zA-Z]{2,}")
                && this.emailTxt.getText().toString().matches("[a-zA-Z0-9]{3,}.*[@]{1}[a-zA-Z]{2,}+.{2,5}")
                && this.phoneNoTxt.getText().toString().matches("[+]{1}[0-9]{12}")
                && this.passTxt.getText().toString().matches("[a-zA-Z0-9[\\W]+]{8,}")
                && validatePassTxt.getText().toString().equals(passTxt.getText().toString()))
        {
            return true;
        }
        else{
            if(!this.nameTxt.getText().toString().matches("[a-zA-Z]{2,}"))
            {
                nameTxt.requestFocus();
                nameTxt.setError("Please only enter letters to the name field");
            }
            if(!this.surnameTxt.getText().toString().matches("[a-zA-Z]{2,}")){
                surnameTxt.requestFocus(); surnameTxt.setError("Please only enter letters to the surname field");
            }
            if(!this.emailTxt.getText().toString().matches("[a-zA-Z0-9]{3,}.*[@]{1}[a-zA-Z]{2,}+.{2,5}")){

                emailTxt.requestFocus(); emailTxt.setError("PLease enter a valid email");
            }
            if(!this.passTxt.getText().toString().matches("[a-zA-Z0-9[\\W]+]{8,}"))
            {
                passTxt.requestFocus();
                passTxt.setError("Passwords should be at least 8 characters with at least one uppercase letter,number and symbol");
            }
            if(!validatePassTxt.getText().toString().equals(passTxt.getText().toString())){
                validatePassTxt.setError("Passwords do not match each other");
                validatePassTxt.requestFocus();
            }
            if(!this.phoneNoTxt.getText().toString().matches("[+]{1}[0-9]{12}")){
                phoneNoTxt.setError("Please enter a valid phone number");
                phoneNoTxt.requestFocus();
            }
            return false;
        }
    }
    public void RegisterUser()
    {
        mAuth.createUserWithEmailAndPassword(emailTxt.getText().toString(),passTxt.getText().toString()).addOnCompleteListener(this);
    }

    public void buutttonOnClick(View v)
    {
        if (v.getId()==R.id.Signin)
        {
            startActivity(new Intent(Activitysignup.this,Activitylogin.class));
        }
    }
}
