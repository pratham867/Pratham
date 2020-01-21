package com.example.login_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Mains extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);
    }

    public void out(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
