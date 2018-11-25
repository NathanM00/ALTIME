package com.example.altime.altime;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CalendarFragment extends Fragment {
    View vista;

    CalendarView calendar;
    Button btn_agregar;
    String mifecha;
    ListView listaEventos;
    Bundle bundle = new Bundle();
    EventoAdapter eventoAdapter;

    FirebaseDatabase db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendar = vista.findViewById(R.id.calendar);
        btn_agregar = vista.findViewById(R.id.btn_agregar);
        listaEventos = vista.findViewById(R.id.listaEventos);

        eventoAdapter = new EventoAdapter(vista.getContext());
        listaEventos.setAdapter(eventoAdapter);


        listaEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento evento = eventoAdapter.getItem(position);
                bundle.putString("nombre", evento.getNombre());
                bundle.putString("descripcion", evento.getDescripcion());
                bundle.putString("fecha", evento.getFechacreacion());
                setArguments(bundle);

                Fragment fragment = new DetallesEventoFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        db = FirebaseDatabase.getInstance();
        DatabaseReference eventos_ref = db.getReference().child("eventos");
        eventos_ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Evento evento = dataSnapshot.getValue(Evento.class);
                eventoAdapter.addEvent(evento);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mifecha = dayOfMonth + "/" + (month + 1) + "/" + year;

                bundle.putString("fecha", mifecha);
                setArguments(bundle);

                
            }
        });

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "fecha" + mifecha, Toast.LENGTH_SHORT).show();

                Fragment fragment = new AgregarEventoFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        return vista;
    }
}
