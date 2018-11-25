package com.example.altime.altime;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CoeFragment extends Fragment {
    View vista;

    FirebaseAuth auth;
    FirebaseDatabase db;

    Button btn_escrituracoe;
    Button btn_lecturacoe;

    /*String child;
    String valor = "coe";*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if(getArguments() !=null){
            child = getArguments().getString(MainActivity.KEY_CHILD);

        }*/

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_coe,container,false);

        btn_escrituracoe = vista.findViewById(R.id.btn_escrituracoe);
        btn_lecturacoe = vista.findViewById(R.id.btn_lecturacoe);

        btn_lecturacoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction frag = getFragmentManager().beginTransaction();
                frag.replace(R.id.fragment_contenedor, new TimerFragment());
                frag.commit();

            }
        });

        //Toast.makeText(getContext(), "el child es: " + child, Toast.LENGTH_LONG).show();

        return vista;
    }
}
