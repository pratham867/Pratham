package com.example.login_2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register_2 extends AppCompatActivity {
    EditText Name , Phone , mail , pass,pass2;
    Button reg;
    TextView  alr ;
    FirebaseAuth fAuth;
    ProgressBar barr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_2);
        Name = findViewById(R.id.name);
        Phone = findViewById(R.id.phone);
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        pass2 = findViewById(R.id.pass2);
        reg = findViewById(R.id.btn2);
        alr = findViewById(R.id.reg2);
        barr = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser()!= null)
        {
            startActivity(new Intent(getApplicationContext(), Mains.class));
            finish();
        }

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString().trim();
                String password = pass.getText().toString().trim();
//                String password2 = pass2.getText().toString().trim();

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

                barr.setVisibility(View.VISIBLE);
                //registering user in firebasesss
                fAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register_2.this, "User is Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Mains.class));
                        }else {
                            Toast.makeText(Register_2.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            barr.setVisibility(View.GONE);
                        }
                    }
                } );
            }
        });
    }

    public void already(View view) {
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);
    }
}
