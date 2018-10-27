package com.example.toshiba.pierdapt;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Puntajes extends ListActivity {

    /*ListaJugadores lstJugadores;
    ArrayList<Player> lstPlayers;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new AdaptadorPersonalizado(this));
       /* lstJugadores = (ListaJugadores)this.getIntent().getExtras().getSerializable("Jugadores");
        lstPlayers = lstJugadores.getLstJugadores();

        setListAdapter( new AdatadorPersonalizado(this,lstPlayers,R.layout.xml_datos));*/

    }


    /*public void onListItemClick(ListView lista, View padre,int posicion, long id ){
        Player jugador = this.lstPlayers.get(posicion);
        Toast.makeText(this, "El departamento seleccionado es: " + jugador.getNombre(),
                Toast.LENGTH_LONG).show();
    }*/

}
