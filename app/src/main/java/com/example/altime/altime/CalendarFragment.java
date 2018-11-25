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
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarFragment extends Fragment {
    View vista;

    CalendarView calendar;
    Button btn_agregar;
    String mifecha;
    Bundle bundle = new Bundle();

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

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mifecha = dayOfMonth + "/" + month + 1 + "/" + year;

                bundle.putString("fecha",mifecha);
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
