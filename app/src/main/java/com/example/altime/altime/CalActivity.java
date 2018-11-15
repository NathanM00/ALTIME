package com.example.altime.altime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class CalActivity extends AppCompatActivity{

    FirebaseDatabase db;
    FirebaseAuth auth;
    Button btn_salir;
    BottomNavigationView nv_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        btn_salir =findViewById(R.id.btn_salir);
        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        nv_bar = findViewById(R.id.main_nav);

        FirebaseUser usuario = auth.getCurrentUser();

      btn_salir.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              auth.signOut();
              finish();
              startActivity( new Intent(CalActivity.this, LoginActivity.class));

          }
      });


    }

    }
