package com.example.altime.altime;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PetEditFragment extends Fragment {

    View vista;
    ImageButton btn_petEditAtras;
    ImageButton btn_bloq1;
    ImageButton btn_bloq2;
    ImageButton btn_bloq3;
    ImageButton btn_bloq4;
    ImageButton btn_bloq5;
    ImageButton btn_bloq6;
    ImageButton btn_bloq7;
    ImageButton btn_bloq8;
    ImageButton btn_brerrow;
    boolean puesto = false;
    TextView tv_objeto;
    ImageView franckie;
    Bundle bundle = new Bundle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() !=null) {
            puesto = getArguments().getBoolean("puesto");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_petedit, container, false);

        btn_brerrow=vista.findViewById(R.id.btn_brerrow);
        btn_bloq1=vista.findViewById(R.id.btn_bloq1);
        btn_bloq2=vista.findViewById(R.id.btn_bloq2);
        btn_bloq3=vista.findViewById(R.id.btn_bloq3);
        btn_bloq4=vista.findViewById(R.id.btn_bloq4);
        btn_bloq5=vista.findViewById(R.id.btn_bloq5);
        btn_bloq6=vista.findViewById(R.id.btn_bloq6);
        btn_bloq7=vista.findViewById(R.id.btn_bloq7);
        btn_bloq8=vista.findViewById(R.id.btn_bloq8);
        franckie= vista.findViewById(R.id.iv_franckieEdit);
        tv_objeto = vista.findViewById(R.id.tv_objeto);

        btn_petEditAtras = vista.findViewById(R.id.btn_petEditAtras);

        if(!puesto) {
            franckie.setImageResource(R.drawable.mascota_petedit);
            puesto = false;
            btn_brerrow.setBackgroundResource( R.drawable.btn_brerrow);
            tv_objeto.setText("Ninguno");
        }else{
            franckie.setImageResource(R.drawable.franc_cap);
            puesto = true;
            tv_objeto.setText("Birrete");
            btn_brerrow.setBackgroundResource(R.drawable.btn_gorroselec) ;
            }

        btn_petEditAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new PetFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction  = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        cambioDeRopa();
        botonesBloqueados();
        return vista;
    }

    private void botonesBloqueados(){

        btn_bloq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "No has debloqueado este objeto\nSigue estudiando!", Toast.LENGTH_SHORT).show();
            }
        });
        btn_bloq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "No has debloqueado este objeto\nSigue estudiando!", Toast.LENGTH_SHORT).show();
            }
        });
        btn_bloq3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "No has debloqueado este objeto\nSigue estudiando!", Toast.LENGTH_SHORT).show();
            }
        });
        btn_bloq4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "No has debloqueado este objeto\nSigue estudiando!", Toast.LENGTH_SHORT).show();
            }
        });
        btn_bloq5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "No has debloqueado este objeto\nSigue estudiando!", Toast.LENGTH_SHORT).show();
            }
        });
        btn_bloq6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "No has debloqueado este objeto\nSigue estudiando!", Toast.LENGTH_SHORT).show();
            }
        });
        btn_bloq7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "No has debloqueado este objeto\nSigue estudiando!", Toast.LENGTH_SHORT).show();
            }
        });
        btn_bloq8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "No has debloqueado este objeto\nSigue estudiando!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cambioDeRopa(){

        btn_brerrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!puesto) {
                    btn_brerrow.setBackgroundResource(R.drawable.btn_gorroselec) ;
                    franckie.setImageResource(R.drawable.franc_cap);
                    puesto = true;
                    bundle.putBoolean("puesto", puesto);
                    tv_objeto.setText("Birrete");
                }else{
                    btn_brerrow.setBackgroundResource( R.drawable.btn_brerrow);
                    franckie.setImageResource(R.drawable.mascota_petedit);
                    puesto = false;
                    bundle.putBoolean("puesto", puesto);
                    tv_objeto.setText("Ninguno");
                }
            }
        });
    }


}
