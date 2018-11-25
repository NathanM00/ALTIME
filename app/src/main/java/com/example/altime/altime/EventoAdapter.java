package com.example.altime.altime;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventoAdapter extends BaseAdapter {

    private ArrayList<Evento> eventos;
    private Context contexto;

    public EventoAdapter(Context contexto) {
        this.contexto = contexto;
        eventos = new ArrayList<Evento>();
    }

    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Evento getItem(int position) {
        return eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View renglonActividad = inflater.inflate(R.layout.renglon, null);

        TextView tv_nombre = renglonActividad.findViewById(R.id.renglon_nombre);
        TextView tv_fecha = renglonActividad.findViewById(R.id.renglon_fecha);
        TextView tv_descripcion = renglonActividad.findViewById(R.id.renglon_fecha);

        Evento evento = eventos.get(position);
        tv_descripcion.setText(evento.getDescripcion());
        tv_fecha.setText(evento.getFechacreacion());
        tv_nombre.setText(evento.getNombre());

        return renglonActividad;
    }

    public void addEvent(Evento evento) {
        eventos.add(evento);
        notifyDataSetChanged();
    }
}
