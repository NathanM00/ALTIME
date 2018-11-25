package com.example.altime.altime;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AgregarEventoFragment extends Fragment {
    View vista;

    EditText ed_nombreEvento;
    EditText ed_descripcionEvento;
    TextView btn_cancelarEvento;
    Button btn_guardarEvento;
    String fechaSeleccionada;

    DatabaseReference reference;

    FirebaseDatabase db;
    FirebaseAuth auth;
    FirebaseListAdapter<Evento> adapter;

    Bundle bundle = new Bundle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fechaSeleccionada = getArguments().getString("fecha", "Pos es null");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_agregarevento, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        ed_nombreEvento = vista.findViewById(R.id.ed_nombreEvento);
        ed_descripcionEvento = vista.findViewById(R.id.ed_descripcionEvento);
        btn_cancelarEvento = vista.findViewById(R.id.btn_cancelarEvento);
        btn_guardarEvento = vista.findViewById(R.id.btn_guardarEvento);


        btn_guardarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre_evento = ed_nombreEvento.getText().toString();
                String descripcion_evento = ed_descripcionEvento.getText().toString();
                //la fecha que no se traer del otro fragment
                //fechaSeleccionada = "una fecha";
                //hacer la resta entre la fecha de hoy y la seleccionada
                //String dias_restantes = "numero dias";

                //db.getReference().child("eventos").child(nombre_evento);
                Evento evento = new Evento();
                evento.setDescripcion(descripcion_evento);
                evento.setFechacreacion(fechaSeleccionada);
                evento.setDiasrestantes("10");
                evento.setNombre(nombre_evento);
                evento.setUid(System.currentTimeMillis() + "");

                db.getReference().child("eventos").push().setValue(evento);

                Toast.makeText(getContext(), "fecha" + fechaSeleccionada, Toast.LENGTH_SHORT).show();


                Fragment fragment = new CalendarFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_cancelarEvento.setOnClickListener(new View.OnClickListener() {
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
