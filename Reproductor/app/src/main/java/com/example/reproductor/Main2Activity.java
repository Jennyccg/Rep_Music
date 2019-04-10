package com.example.reproductor;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    String nPieza;
    int numPieza;
    String pos;
  //  ListView lvm;
    ImageView iv;
    Button play_stop;
    Button btn_repita;
    ArrayList<String> arreglo;
    ArrayAdapter adaptadorMusica;
    MediaPlayer reproductorMusica;
    private TextView tv_Musica;
    int repetir = 2, posicion=0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
      //  lvm = (ListView) findViewById(R.id.lv_music2);
        play_stop = (Button)findViewById(R.id.btn_play2);
        btn_repita = (Button)findViewById(R.id.btn_repetir2);
        iv=(ImageView)findViewById(R.id.iv_imagen2);



        arreglo = getIntent().getStringArrayListExtra("lista");
        nPieza = getIntent().getStringExtra("numero");

         pos = getIntent().getStringExtra("posi");
         posicion = Integer.parseInt(pos);
        if (posicion== 0)
            iv.setImageResource(R.drawable.headphones);
        else if( posicion == 1 )
            iv.setImageResource(R.drawable.headphones);
        else
            iv.setImageResource(R.drawable.headphones);

        numPieza =  Integer.parseInt(nPieza);

        adaptadorMusica = new ArrayAdapter  (this,android.R.layout.simple_list_item_1, arreglo);

       // lvm.setAdapter(adaptadorMusica);    SE QUITA PARA LIBERAR  LA LISTA (LISTVIEW)
         if (reproductorMusica != null){
            reproductorMusica.release();
        }
        reproductorMusica = reproductorMusica.create(Main2Activity.this,numPieza);
        reproductorMusica.start();
    }

    // Play Song
    public void ReproducirPausar (View view){

        if(reproductorMusica.isPlaying()){
            reproductorMusica.pause();
            play_stop.setBackgroundResource(R.drawable.play);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }
        else
        {
            numPieza = getResources().getIdentifier(arreglo.get(posicion),"raw",getPackageName());
            reproductorMusica = reproductorMusica.create(Main2Activity.this,numPieza);
            reproductorMusica.start();
            play_stop.setBackgroundResource(R.drawable.pause);
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        }
    }

    // stop
    public void Stop (View view){
        if (reproductorMusica != null ){
            reproductorMusica.stop();

        }
    }

    // metodo siguiente

    public void Siguiente (View view){

        Toast.makeText(this, "Ingresando a Siguiente", Toast.LENGTH_SHORT).show();

        if (posicion < arreglo.size() -1 ){
            if (reproductorMusica.isPlaying()) {
                reproductorMusica.stop();
                posicion++;
                numPieza = getResources().getIdentifier(arreglo.get(posicion),"raw",getPackageName());
                reproductorMusica = reproductorMusica .create(this,numPieza);
                Toast.makeText(this, "Siguiente", Toast.LENGTH_SHORT).show();
                reproductorMusica.start();
               if (posicion == 0)
                      iv.setImageResource(R.drawable.headphones);
               else if( posicion == 1 )
                   iv.setImageResource(R.drawable.headphones);
                    else
                   iv.setImageResource(R.drawable.headphones);
            }
            else
            {
                posicion++;
                   if (posicion== 0)
                      iv.setImageResource(R.drawable.headphones);
                   else if( posicion == 1 )
                         iv.setImageResource(R.drawable.headphones);
                        else
                    iv.setImageResource(R.drawable.headphones);
            }
        }
        else
        {
            Toast.makeText(this, "No hay más canciones", Toast.LENGTH_SHORT).show();
        }
    }

// metodo para canción anterior

    public void Anterior(View view){

        if (posicion >=1 ){
            if (reproductorMusica.isPlaying()){
                reproductorMusica.stop();
                posicion--;
                numPieza = getResources().getIdentifier(arreglo.get(posicion),"raw",getPackageName());
                reproductorMusica = reproductorMusica .create(this,numPieza);
                Toast.makeText(this, "Reproducir", Toast.LENGTH_SHORT).show();
                reproductorMusica.start();
                if (posicion== 0)
                    iv.setImageResource(R.drawable.headphones);
                else if( posicion == 1 )
                    iv.setImageResource(R.drawable.headphones);
                else
                    iv.setImageResource(R.drawable.headphones);
            }
   //         else{
   //             posicion --;
   //         }
        }
        else
        {
            Toast.makeText(this, "No hay más canciones", Toast.LENGTH_SHORT).show();
        }
    }


    // metodo repetir una pista

    public void Repetir (View view){
        if(repetir ==1) {
            btn_repita.setBackgroundResource(R.drawable.repeat);
            Toast.makeText(this, "No Repetir", Toast.LENGTH_SHORT).show();
            reproductorMusica.setLooping(false);
            repetir =2;

        }
        else {
            btn_repita.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
            reproductorMusica.setLooping(true);
            repetir =1;
        }
    }



    public void regresar (View view) {
        reproductorMusica.stop();
        Intent siguiente = new Intent(this, MainActivity.class);
        startActivity(siguiente);
        finish();
    }

}
