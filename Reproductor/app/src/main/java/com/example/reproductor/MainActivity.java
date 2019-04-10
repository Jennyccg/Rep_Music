package com.example.reproductor;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listaArreglo;
    ListView lv_musica;

    ArrayAdapter adaptadorMusica;
    MediaPlayer reproductorMusica;
    int  numPieza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_musica = (ListView) findViewById(R.id.lv_musica);
        listaArreglo = new ArrayList<String>();
        Field[] campos = R.raw.class.getFields();

        for (int i = 0; i < campos.length;i++){
            listaArreglo.add(campos[i].getName());
            Toast.makeText(this,campos[i].getName() , Toast.LENGTH_SHORT).show();
        }
        adaptadorMusica = new ArrayAdapter (this,android.R.layout.simple_list_item_1, listaArreglo);
        lv_musica.setAdapter(adaptadorMusica);





        lv_musica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {

                  numPieza = getResources().getIdentifier(listaArreglo.get(posicion),"raw",getPackageName());
                ventana2(String.valueOf(posicion));


              //










            }
        });

    }

public void ventana2(String posicio){

   // Toast.makeText(this, posicion, Toast.LENGTH_SHORT).show();

    Intent siguiente = new Intent( this,Main2Activity.class);

    // envia un parametros
    siguiente.putExtra("lista",listaArreglo);
    siguiente.putExtra("numero",String.valueOf(numPieza));


    siguiente.putExtra("posi", posicio);


    startActivity(siguiente);
}


    public void Terminar (View view){
        //System.exit(0);
        finish();
    }

}
