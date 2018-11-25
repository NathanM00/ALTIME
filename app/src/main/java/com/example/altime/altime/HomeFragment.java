package com.example.altime.altime;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FirebaseDatabase db;
    ListView lv;
    View vista;
    ArrayList<Noticia> noticias = new ArrayList<>();
    CustomAdapter adapter;
    DatabaseReference referencia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_home, container, false);
        lv = vista.findViewById(R.id.lv_noticias);

        // paso 1 para vincular la base de datos
        db = FirebaseDatabase.getInstance();

        // paso 2 crear la consulta a la base de datos
        referencia = db.getReference().child("noticias");// cambiar referencia

        escucharNoticiasFirebase();

        //llenarArreglo();

        adapter = new CustomAdapter(noticias, getActivity());


        // agregar el adapter a la lista
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(noticias.get(position).getTiponoticia().equals("1")) {
                    Fragment fragment = new CalendarFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contenedor,
                            fragment).commit();
                }
                if(noticias.get(position).getTiponoticia().equals("2")) {
                    Fragment fragment = new PetFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contenedor,
                            fragment).commit();
                }
            }
        });


        return vista;

    }

    private void llenarArreglo(DataSnapshot dataSnapshot) {
        noticias.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
           // Log.d("pap", "Llego nodo de Firebase ");
            Noticia noticia = ds.getValue(Noticia.class);
            //Log.d("pap", "Llego nodo de Firebase " + noticia.getTiponoticia());
            //Log.d("pap", "Llego nodo de Firebase " + noticia.getTexto());

            noticias.add(noticia);
        }
        adapter.setDatos(noticias);
    }

    void escucharNoticiasFirebase() {
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Log.d("pap", "Llego nodo de Firebase " + dataSnapshot.getKey());
                llenarArreglo(dataSnapshot);
                //Log.d("pap", "Ejecuto setAdapter...!!!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
