package com.example.login_2;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static int  SPLASH_TIME_OUT = 1000;
    EditText mail,pass;
    FirebaseAuth fAuth;
    Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, Register_2.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        fAuth= FirebaseAuth.getInstance();
        log = findViewById(R.id.log);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    mail.setError("Email is empty");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    pass.setError("Password is empty");
                    return;
                }

                if(password.length()<6){
                    pass.setError("Password must be >6");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Mains.class));
                        }else {
                            Toast.makeText(MainActivity.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void Another(View view) {
        Intent intent = new Intent(this, Register_2.class);
        startActivity(intent);
    }

    public void forgot(View view) {
        Intent i = new Intent(this,forgot.class);
        startActivity(i);
    }
}
