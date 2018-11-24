package com.example.altime.altime;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class TimerFragment extends Fragment {
    Activity activity;

    View vista;

    Chronometer chronometer;
    ImageButton play;
    ImageButton pause;
    ImageView descnaso;

    long pauseOffSet;

    boolean tiempoCorre;

    FirebaseDatabase database;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_timer, container,false);

        database = FirebaseDatabase.getInstance();

        chronometer = vista.findViewById(R.id.chronometer);
        chronometer.setFormat("Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if((SystemClock.elapsedRealtime()- chronometer.getBase()) >= 750000 && (SystemClock.elapsedRealtime()- chronometer.getBase())<= 753000){
                    //chronometer.setBase(SystemClock.elapsedRealtime());
                    Vibrator alert = (Vibrator) getContext().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    alert.vibrate(1000);
                    descnaso.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Hora de la pausa activa!!", Toast.LENGTH_SHORT).show();
                }else{
                    descnaso.setVisibility(View.INVISIBLE);
                }

            }
        });

        play = vista.findViewById(R.id.plei);
        pause = vista.findViewById(R.id.pause);
        descnaso = vista.findViewById(R.id.descanso);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChronometer();
                Toast.makeText(getContext(), "Comienza el estudio!!", Toast.LENGTH_SHORT).show();
                play.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseChronometer();

                pause.setVisibility(View.INVISIBLE);
                play.setVisibility(View.VISIBLE);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setCancelable(true);
                builder.setTitle("Notificacion");
                builder.setMessage("Deseas guardar tu tiempo actual?");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String savedTime = chronometer.getText().toString();
                        Log.e("Tiempo en String", ""+savedTime);
                        database.getReference().child("timer").push().setValue(savedTime);
                        Toast.makeText(getContext(), "Tu tiempo ha sido guardado", Toast.LENGTH_LONG).show();
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



        return vista;
    }

    public void startChronometer(){
        if(!tiempoCorre){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffSet);
            chronometer.start();
            tiempoCorre = true;
        }

    }

    public void pauseChronometer(){
        if(tiempoCorre){
            chronometer.stop();
            pauseOffSet = SystemClock.elapsedRealtime() - chronometer.getBase();
            tiempoCorre = false;
        }
    }

    public void saveChronometer(){

    }
}
