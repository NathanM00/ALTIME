package com.example.altime.altime;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetallesEventoFragment extends Fragment {
    View vista;

    Bundle bundle = new Bundle();
    TextView nombreEvento;
    TextView descripcionEvento;
    TextView fechaEvento;
    ImageButton btn_eliminar;
    Button btn_volver;
    String nombre;
    String descripcion;
    String fechaSeleccionada;

    FirebaseDatabase db;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_detallesevento, container, false);

        db = FirebaseDatabase.getInstance();

        nombreEvento = vista.findViewById(R.id.nombreEventoDetalles);
        descripcionEvento = vista.findViewById(R.id.descripcionEventoDetalles);
        fechaEvento = vista.findViewById(R.id.fechaSeleccionadaDetalles);

        if (getArguments() != null) {
            nombre = getArguments().getString("nombre", "no hay nombre?");
            descripcion = getArguments().getString("descripcion", "no hay descripción?");
            fechaSeleccionada = getArguments().getString("fecha", "y la fecha?");
        }

        nombreEvento.setText(nombre);
        descripcionEvento.setText(descripcion);
        fechaEvento.setText(fechaSeleccionada);
        btn_eliminar = vista.findViewById(R.id.btn_eliminarEvento);

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setCancelable(true);
                builder.setTitle("Espera...!");
                builder.setMessage("¿Seguro quieres eliminar este evento?");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
                        Toast.makeText(getContext(), "Se ha eliminado un evento", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
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
