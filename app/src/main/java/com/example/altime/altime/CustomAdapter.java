package com.example.altime.altime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Noticia> implements View.OnClickListener {

    private ArrayList<Noticia> dataSet;
    Context mContext;

    public CustomAdapter(ArrayList<Noticia> data, Context context) {
        super(context,R.layout.renglonnoticia2,data);
        dataSet = data;
        mContext = context;
    }
    // View lookup cache
    private static class ViewHolder {
        TextView texto;
        TextView diasemana;
        TextView numerodia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Noticia noticia = getItem(position);

        ViewHolder viewHolder;
        final View result;

        if (true) {
            //if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            if(noticia.getTiponoticia().equals("1")) {
                convertView = inflater.inflate(R.layout.renglonnoticia1, parent, false);
                viewHolder.texto = (TextView) convertView.findViewById(R.id.tv_contenidoNoticia1);
                viewHolder.diasemana = (TextView) convertView.findViewById(R.id.tv_diaNoticia1);
                viewHolder.numerodia = (TextView) convertView.findViewById(R.id.tv_numeroNoticia1);
                result=convertView;
                convertView.setTag(viewHolder);
            }
            else {
                convertView = inflater.inflate(R.layout.renglonnoticia2, parent, false);
                viewHolder.texto = (TextView) convertView.findViewById(R.id.tv_noticia2);
                result=convertView;
                convertView.setTag(viewHolder);
            }


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        if(noticia.getTiponoticia().equals("1")) {
            viewHolder.texto.setText(noticia.getTexto());
            viewHolder.diasemana.setText(noticia.getDiasemana());
            viewHolder.numerodia.setText(noticia.getNumerodia());

        }else{
            viewHolder.texto.setText(noticia.getTexto());
        }

        return convertView;

    }

    public void setDatos(ArrayList<Noticia> data){
        dataSet =data;
        notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {

    }
}

