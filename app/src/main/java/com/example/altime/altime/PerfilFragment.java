package com.example.altime.altime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PerfilFragment extends Fragment {
    View vista;

    TextView nombre;
    TextView codigo;

    ImageButton btn_coe;
    ImageButton btn_algebra;
    ImageButton btn_logica;
    ImageButton btn_bidi;
    ImageButton btn_intro;

    Bundle bundle = new Bundle();

    FirebaseDatabase db;

    Button btn_coe;
    Button btn_algebra;
    Button btn_logica;
    Button btn_bidi;
    Button btn_intro;
    RelativeLayout lecturaunoxxd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_perfil, container,false);

        db = FirebaseDatabase.getInstance();

        nombre = vista.findViewById(R.id.tv_nombre);
        codigo = vista.findViewById(R.id.tv_codigo);
        btn_coe = vista.findViewById(R.id.btn_materiacoe);
        btn_algebra = vista.findViewById(R.id.btn_materiaalgebra);
        btn_logica = vista.findViewById(R.id.btn_materialogica);
        btn_intro = vista.findViewById(R.id.btn_materiaintro);
        btn_bidi = vista.findViewById(R.id.btn_materiabidi);

        DatabaseReference ref = db.getReference().child("usuarios");

        lecturaunoxxd = vista.findViewById(R.id.lecturaunoxxd);
        btn_coe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CoeFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });

        btn_logica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new LogicaFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });

        btn_algebra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AlgebraFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });

        btn_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new IntroFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });
        lecturaunoxxd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_bidi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new BidiFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });

        return vista;
    }
}
