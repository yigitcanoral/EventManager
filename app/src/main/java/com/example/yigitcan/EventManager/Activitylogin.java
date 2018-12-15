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

public class Activitylogin extends AppCompatActivity implements OnCompleteListener,View.OnClickListener {

    private Button login;
    private EditText emaillogin,passwordlogin;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fauth=FirebaseAuth.getInstance();
        login=(Button)(findViewById(R.id.finishLoginBtn));
        login.setOnClickListener(this);
        emaillogin=(EditText)(findViewById(R.id.editText));
        passwordlogin=(EditText)(findViewById(R.id.editText2));
        (findViewById(R.id.textView4)).setOnClickListener(this);
        (findViewById(R.id.textView5)).setOnClickListener(this);

    }
    private void userLogin(){
        fauth.signInWithEmailAndPassword(emaillogin.getText().toString(),passwordlogin.getText().toString()).addOnCompleteListener(this);
    }
    public boolean isValid()
    {
        if (this.emaillogin.getText().toString().matches("[a-zA-Z0-9]{3,}.*[@]{1}[a-zA-Z]{2,}+.{2,5}")
                &&this.passwordlogin.getText().toString().matches("[a-zA-Z0-9[\\W]+]{8,}"))
        {
            return true;
        }
        else
        {
            if(!this.emaillogin.getText().toString().matches("[a-zA-Z0-9]{3,}.*[@]{1}[a-zA-Z]{2,}+.{2,5}")){

                emaillogin.requestFocus(); emaillogin.setError("PLease enter a valid email");
            }
            if(!this.passwordlogin.getText().toString().matches("[a-zA-Z0-9[\\W]+]{8,}"))
            {
                passwordlogin.requestFocus();
                passwordlogin.setError("Passwords should be at least 8 characters with at least one uppercase letter,number and symbol");
            }
            return false;
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.finishLoginBtn)
            if (isValid())
            {
                userLogin();
            }
        else if(v.getId()==R.id.textView4)
            fauth.getCurrentUser().sendEmailVerification();
        else if(v.getId()==R.id.textView5)
            fauth.sendPasswordResetEmail(emaillogin.getText().toString());
    }

    @Override
    public void onComplete(@NonNull Task task) {
        if(task.isSuccessful()){
            Intent intentToMain=new Intent(Activitylogin.this,Activitymainpage.class);
            intentToMain.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentToMain);
        }else{
            Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}










