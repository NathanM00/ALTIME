package com.example.altime.altime;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    FirebaseAuth auth;
    BottomNavigationView nv_bar;
    ActionBar barra;
    int felicidad=90;
    int energia=40;
    boolean estudiando;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        nv_bar = findViewById(R.id.main_nav);
        barra = getSupportActionBar();
        estudiando = false;

        FirebaseUser usuario = auth.getCurrentUser();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contenedor,
          new HomeFragment()).commit();


        barraDeNavegacion();
        barraProgreso();
        notificacionMascota();
    }

    private void barraDeNavegacion(){
        nv_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){

                    case R.id.nav_home:
                        fragment = new HomeFragment();

                        estudiando = false;
                        barra.setBackgroundDrawable( new ColorDrawable( Color.parseColor("#E8475B"))  );
                        barra.setTitle("AlTIME");
                        nv_bar.setItemIconTintList( getColorStateList(R.color.nav_item_menu));
                        nv_bar.setItemTextColor( getColorStateList(R.color.nav_item_menu));
                        break;

                    case R.id.nav_cal:
                        fragment = new CalendarFragment();

                        estudiando = false;
                        barra.setBackgroundDrawable( new ColorDrawable( Color.parseColor("#F18768"))  );
                        barra.setTitle("Calendario");
                        nv_bar.setItemIconTintList( getColorStateList(R.color.nav_item_cal));
                        nv_bar.setItemTextColor( getColorStateList(R.color.nav_item_cal));
                        break;

                    case R.id.nav_pet:
                        fragment = new PetFragment();
                        fragment.setArguments(bundle);

                        estudiando = false;
                        barra.setBackgroundDrawable( new ColorDrawable( Color.parseColor("#FF9801"))  );
                        barra.setTitle("Mascota");
                        nv_bar.setItemIconTintList( getColorStateList(R.color.nav_item_pet));
                        nv_bar.setItemTextColor( getColorStateList(R.color.nav_item_pet));
                        break;

                    case R.id.nav_perfil:
                        fragment = new PerfilFragment();

                        estudiando = true;
                        barra.setBackgroundDrawable( new ColorDrawable( Color.parseColor("#FEC82A"))  );
                        barra.setTitle("Perfil");
                        nv_bar.setItemIconTintList( getColorStateList(R.color.nav_item_perfil));
                        nv_bar.setItemTextColor( getColorStateList(R.color.nav_item_perfil));
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contenedor,
                        fragment).commit();
                return  true;
            }
        });

    }

    private void notificacionMascota(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (felicidad == 50) {

                        Noticia noticia = new Noticia();
                        noticia.setTiponoticia("2");
                        noticia.setIdnoticia("10101");
                        noticia.setTexto("Franckie esta muy triste, estudia un poco para subirle el animo!");
                        noticia.setDiasemana(" ");
                        noticia.setNumerodia(" ");
                        db.getReference().child("noticias").child("10101").setValue(noticia);
                        break;
                    }
                }
            }
        }).start();
    }

    private  void barraProgreso(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (estudiando) {
                        felicidad++;
                        energia--;
                        android.os.SystemClock.sleep(1500);
                        if(felicidad >=100){
                            felicidad =100;
                        }
                        if(energia <=0){
                            energia =0;
                        }
                        bundle.putInt("felicidad", felicidad);
                        bundle.putInt("energia", energia);
                    } else {
                        felicidad--;
                        energia++;
                        android.os.SystemClock.sleep(1500);
                        if(energia >=100){
                            energia =100;
                        }
                        if(felicidad <=0){
                            felicidad =0;
                        }
                        bundle.putInt("felicidad", felicidad);
                        bundle.putInt("energia", energia);
                    }
                }
            }
        }).start();


    }


}