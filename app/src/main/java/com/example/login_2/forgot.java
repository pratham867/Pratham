package com.example.login_2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot extends AppCompatActivity {

    EditText e1;
    Button b1;
    FirebaseAuth FAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        e1 = findViewById(R.id.editText);
        b1 = findViewById(R.id.button);

        FAuth = FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAuth.sendPasswordResetEmail(e1.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(forgot.this,"Password sent to your mail",Toast.LENGTH_LONG).show();
                    }else
                    { Toast.makeText(forgot.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                    }
                });
            }
        });
    }
}
