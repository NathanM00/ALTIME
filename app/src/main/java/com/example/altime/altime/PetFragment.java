package com.example.altime.altime;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class PetFragment extends Fragment {

    View vista;
    FloatingActionButton btn_petMenu;
    ProgressBar pgb_Felicidad;
    ProgressBar pgb_Energia;
    int felicidad =100;
    int energia=80;
    boolean puesto= false;
    ImageView franckie;
    Bundle bundle = new Bundle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Felicidad original",""+felicidad);
        Log.e("Energia original",""+energia);

        if(getArguments() !=null) {

            Log.e("Felicidad que llega",""+ getArguments().getInt("felicidad"));
            Log.e("Energia que llega",""+ getArguments().getInt("energia"));

            felicidad = getArguments().getInt("felicidad");
            energia = getArguments().getInt("energia");
            puesto = getArguments().getBoolean("puesto");
        }
        Log.e("Felicidad cambiada",""+felicidad);
        Log.e("Energia cambiada",""+energia);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_pet, container, false);
        btn_petMenu = vista.findViewById(R.id.btn_petMenu);
        pgb_Felicidad = vista.findViewById(R.id.pgb_felicidad);
       pgb_Energia = vista.findViewById(R.id.pgb_energia);
        franckie = vista.findViewById(R.id.iv_franckiePet);

        if(!puesto) {
            franckie.setImageResource(R.drawable.mascota_petedit);
            puesto = false;
            bundle.putBoolean("puesto", puesto);

        }else{
            franckie.setImageResource(R.drawable.franc_cap);
            puesto = true;
            bundle.putBoolean("puesto", puesto);
        }

        btn_petMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PetEditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction  = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });

        pgb_Felicidad.setProgress(felicidad);
        pgb_Energia.setProgress(energia);
        barraProgreso();

        return vista;
    }

    private  void barraProgreso(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (felicidad >=0 && energia <=100){
                    felicidad--;
                    energia++;
                    android.os.SystemClock.sleep(1500);
                    pgb_Felicidad.setProgress(felicidad);
                    pgb_Energia.setProgress(energia);
                }
            }
        }).start();
    }

}
