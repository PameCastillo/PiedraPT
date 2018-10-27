package com.example.toshiba.pierdapt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    //declaracion de controles
    Button btnjugador;
    Button btnplay;
    Button btnpuntajs;
   // ListaJugadores lstJugadores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Inicializacion de controles
        this.btnjugador = (Button)findViewById(R.id.btnjugador);
        this.btnplay = (Button)findViewById(R.id.btnplay);
        this.btnpuntajs =(Button)findViewById(R.id.btnpuntajs);

       // lstJugadores = new ListaJugadores();

        this.btnjugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(Principal.this, Jugador.class);
                startActivityForResult(intento, 2);
                //finish();
            }
        });

        this.btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(Principal.this, Juego.class);
                intento.putExtra("nick", Jugador.nick);
                startActivityForResult(intento, 3);
                //finish();
            }
        });

        this.btnpuntajs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // intento.putExtra("Jugadores",lstJugadores);

               // finish();
                if(AdaptadorPersonalizado.puntajes[0] != null
                        || AdaptadorPersonalizado.puntajes[1] != null
                        || AdaptadorPersonalizado.puntajes[2] != null)
                {
                    Intent intento = new Intent(Principal.this, Puntajes.class);
                    startActivityForResult(intento, 4);
                }
                else
                {
                    Toast.makeText(Principal.this, "No Existen jugadores", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onActivityResult(int RequestCode, int codeResult, Intent data)
    {
        super.onActivityResult(RequestCode, codeResult, data);
        if(RequestCode == 2)
        {
            if(codeResult==RESULT_OK)
            {
                Toast.makeText(this, data.getStringExtra(Jugador.nick), Toast.LENGTH_SHORT).show();
                /*Player p = new Player(data.getStringExtra(Jugador.nick),0);
                lstJugadores.InsertarJugador(p);*/

            }
        }
        else if(RequestCode == 3)
        {
            if(codeResult == RESULT_OK)
            {
                Toast.makeText(this, data.getStringExtra(Jugador.nick), Toast.LENGTH_SHORT).show();
            }
        }
        else if(RequestCode == 4)
        {
            if(codeResult == RESULT_OK)
            {
                Toast.makeText(this, data.getStringExtra(Jugador.nick), Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
