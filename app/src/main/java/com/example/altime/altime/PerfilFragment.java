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
import android.widget.TextView;

public class PerfilFragment extends Fragment {
    View vista;

    TextView nombre;
    TextView codigo;
    Button btn_coe;
    Button btn_algebra;
    Button btn_logica;
    Button btn_bidi;
    Button btn_intro;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_perfil, container,false);

        nombre = vista.findViewById(R.id.tv_nombre);
        codigo = vista.findViewById(R.id.tv_codigo);
        btn_coe = vista.findViewById(R.id.btn_materiacoe);
        btn_algebra = vista.findViewById(R.id.btn_materiaalgebra);
        btn_logica = vista.findViewById(R.id.btn_materialogica);
        btn_intro = vista.findViewById(R.id.btn_materiaintro);
        btn_bidi = vista.findViewById(R.id.btn_materiabidi);

        btn_coe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction frag = getFragmentManager().beginTransaction();
                frag.replace(R.id.fragment_contenedor, new CoeFragment());
                frag.commit();

                //Intent intent = new Intent(PerfilFragment.this, );
                //startActivity(intent);

            }
        });

        return vista;
    }
}
