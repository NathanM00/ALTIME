package com.example.altime.altime;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity{

    FirebaseDatabase db;
    FirebaseAuth auth;
    Button btn_salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_salir =findViewById(R.id.btn_salir);
        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        FirebaseUser usuario = auth.getCurrentUser();

      btn_salir.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              auth.signOut();
              finish();
              startActivity( new Intent(MenuActivity.this, LoginActivity.class));

          }
      });


    }

    }
