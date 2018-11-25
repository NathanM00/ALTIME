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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetallesEventoFragment extends Fragment {
    View vista;

    Bundle bundle = new Bundle();
    Button btn_eliminar;
    Button btn_volver;
    String nombre;
    FirebaseDatabase db;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_detallesevento, container, false);

        if (getArguments() != null) {
            nombre = getArguments().getString("nombre");
        }

        db = FirebaseDatabase.getInstance();

        btn_eliminar = vista.findViewById(R.id.btn_eliminarEvento);

        Toast.makeText(getContext(), "nombre" + nombre, Toast.LENGTH_SHORT).show();

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.getReference().child("eventos").orderByChild("nombre").equalTo(nombre).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                            String key = postsnapshot.getKey();
                            postsnapshot.getRef().removeValue();
                            Log.e(">> Que elimine?",""+key);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        btn_volver = vista.findViewById(R.id.btn_volver);

        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CalendarFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });
        return vista;
    }
}
