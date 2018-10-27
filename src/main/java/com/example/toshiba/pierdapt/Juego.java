package com.example.toshiba.pierdapt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Juego extends AppCompatActivity {
    //declaracion de variables
    TextView txtjugador;
    String Nick;
    Button btnfin;
    Random rnd;
    Button btnPC;
    Button btnpiedra;
    Button btnpapel;
    Button btntijera;
    //Variables para los ganes, empates, y perdidos
    int ganes=0;
    int empat=0;
    int perd=0;
    int puntajes=0;
    TextView txtganes;
    TextView txtempat;
    TextView txtperd;
    TextView txtpuntajs;
    Button btnnuevo;

   // int n = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        //Inicializacion de controles
        this.txtjugador = (TextView)findViewById(R.id.txtjugador);
        this.btnfin = (Button)findViewById(R.id.btnfin);
        this.btnPC = (Button)findViewById(R.id.btncpu);
        this.btnpiedra = (Button)findViewById(R.id.btnpiedra);
        this.btnpapel = (Button)findViewById(R.id.btnpapel);
        this.btntijera = (Button)findViewById(R.id.btntijera);
        this.txtganes = (TextView)findViewById(R.id.txtganes);
        this.txtempat = (TextView)findViewById(R.id.txtempates);
        this.txtperd = (TextView)findViewById(R.id.txtperdidos);
        this.txtpuntajs = (TextView)findViewById(R.id.txtpuntos);
        this.btnnuevo = (Button)findViewById(R.id.btnnuevo);

        //extrayendo la captura del nombre y pasandolo al textview
        Bundle datos = this.getIntent().getExtras();
        Nick = datos.getString("nick");
        txtjugador.setText(Nick);

        this.btnfin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intento = new Intent(Juego.this, Principal.class);
                startActivity(intento);*/
                Puntaje nuevojugador = new Puntaje();
                nuevojugador.setNombre(txtjugador.getText().toString());
                nuevojugador.setPuntaje(Integer.parseInt(txtpuntajs.getText().toString()));

                //Validamos en que posicion se colocara
                if(AdaptadorPersonalizado.puntajes[0] != null) {
                    if(nuevojugador.getPuntaje() > AdaptadorPersonalizado.puntajes[0].getPuntaje()){
                        //Movemos las posiciones de los tres jugadores
                        AdaptadorPersonalizado.puntajes[2] = AdaptadorPersonalizado.puntajes[1];
                        AdaptadorPersonalizado.puntajes[1] = AdaptadorPersonalizado.puntajes[0];
                        AdaptadorPersonalizado.puntajes[0] = nuevojugador;
                    }else{
                        if(AdaptadorPersonalizado.puntajes[1] != null){
                            if(nuevojugador.getPuntaje() > AdaptadorPersonalizado.puntajes[1].getPuntaje()){
                                //Movemos la posicion de los 2 ultimos jugadores
                                AdaptadorPersonalizado.puntajes[2] = AdaptadorPersonalizado.puntajes[1];
                                AdaptadorPersonalizado.puntajes[1] = nuevojugador;
                            }else{
                                if(AdaptadorPersonalizado.puntajes[2] != null){
                                    //Movemos la posicion del ultimo jugador
                                    if(nuevojugador.getPuntaje() > AdaptadorPersonalizado.puntajes[2].getPuntaje()){
                                        AdaptadorPersonalizado.puntajes[2] = nuevojugador;
                                    }
                                }else {
                                    AdaptadorPersonalizado.puntajes[2] = nuevojugador;
                                }
                            }
                        }else{
                            AdaptadorPersonalizado.puntajes[1] = nuevojugador;
                        }
                    }
                }else {
                    AdaptadorPersonalizado.puntajes[0] = nuevojugador;
                }


                finish();
            }
        });

        this.btnpiedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnpiedra.setText("O");
                randompc();
                /*btnpapel.setEnabled(false);
                btntijera.setEnabled(false);
                btnPC.setEnabled(false);
                btnpiedra.setEnabled(false);*/
                Ganador();
                //limpiabtn();

            }
        });

        this.btnpapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnpapel.setText("[_]");
                randompc();
               /* btnpapel.setEnabled(false);
                btntijera.setEnabled(false);
                btnPC.setEnabled(false);
                btnpiedra.setEnabled(false);*/
                Ganador();
                //limpiabtn();


            }
        });

        this.btntijera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btntijera.setText("X");
                randompc();
                /*btnpapel.setEnabled(false);
                btntijera.setEnabled(false);
                btnPC.setEnabled(false);
                btnpiedra.setEnabled(false);*/
                Ganador();
                //limpiabtn();

            }
        });

        this.btnnuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Limpiar();
            }
        });



        /*this.btnPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randompc();
            }
        });*/


    }

    //Metodo para limpiar los botones
    public void Limpiar()
    {
        btnPC.setText(R.string.btncpu);
        btnpiedra.setText(R.string.btnpiedra);
        btnpapel.setText(R.string.btnpapel);
        btntijera.setText(R.string.btntijera);
    }

    public int numrand() {
        rnd = new Random();
        return 1 + rnd.nextInt(3);
        //return n;
    }

    public void randompc()
    {
       /* String numero;
        numero = String.valueOf(numrand());*/

        int numero = numrand();

        if(numero == 1)
        {
            btnPC.setText("O");
        }
        else if (numero == 2)
        {
            btnPC.setText("[_]");
        }
        else if (numero == 3)
        {
            btnPC.setText("X");
        }
    }

    public void Ganador()
    {
        if (btnPC.getText().toString().equals("O") && btnpiedra.getText().toString().equals("O"))
        {
            Toast.makeText(Juego.this, "Empate", Toast.LENGTH_SHORT).show();
            empat++;
            //txtempat.setText(Integer.toString(empat));

        }
        else if (btnPC.getText().toString().equals("[_]" ) && btnpiedra.getText().toString().equals("O"))
        {
            Toast.makeText(Juego.this, "Gana PC", Toast.LENGTH_SHORT).show();
            perd++;
            txtperd.setText(Integer.toString(perd));
            puntajes = puntajes - 3;
            //txtpuntajs.setText(Integer.toString(puntajes));

        }
        else if (btnPC.getText().toString().equals("X") && btnpiedra.getText().toString().equals("O"))
        {
            Toast.makeText(Juego.this, "Ganaste " + Nick, Toast.LENGTH_SHORT).show();
            ganes++;
            txtganes.setText(Integer.toString(ganes));
            puntajes = puntajes + 6;
            //txtpuntajs.setText(Integer.toString(puntajes));

        }
        else if ( btnPC.getText().toString().equals("O") && btnpapel.getText().toString().equals("[_]"))
        {
            Toast.makeText(Juego.this, "Ganaste " + Nick, Toast.LENGTH_SHORT).show();
            ganes++;
            txtganes.setText(Integer.toString(ganes));
            puntajes = puntajes + 6;

        }
        else if (btnPC.getText().toString().equals("[_]") && btnpapel.getText().toString().equals("[_]"))
        {
            Toast.makeText(Juego.this, "Empate", Toast.LENGTH_SHORT).show();
            empat++;
            txtempat.setText(Integer.toString(empat));

        }
        else if (btnPC.getText().toString().equals("X") && btnpapel.getText().toString().equals("[_]"))
        {
            Toast.makeText(Juego.this, "Gana PC", Toast.LENGTH_SHORT).show();
            perd++;
            txtperd.setText(Integer.toString(perd));
            puntajes = puntajes - 3;
            //txtpuntajs.setText(Integer.toString(puntajes));

        }
        else if (btnPC.getText().toString().equals("O") && btntijera.getText().toString().equals("X"))
        {
            Toast.makeText(Juego.this, "Gana PC", Toast.LENGTH_SHORT).show();
            perd++;
            txtperd.setText(Integer.toString(perd));
            puntajes = puntajes - 3;
            //txtpuntajs.setText(Integer.toString(puntajes));

        }
        else if (btnPC.getText().toString().equals("[_]") && btntijera.getText().toString().equals("X"))
        {
            Toast.makeText(Juego.this, "Ganaste " + Nick, Toast.LENGTH_SHORT).show();
            ganes++;
            txtganes.setText(Integer.toString(ganes));
            puntajes = puntajes + 6;
            //txtpuntajs.setText(Integer.toString(puntajes));

        }
        else if (btnPC.getText().toString().equals("X") && btntijera.getText().toString().equals("X"))
        {
            Toast.makeText(Juego.this, "Empate", Toast.LENGTH_SHORT).show();
            empat++;
            txtempat.setText(Integer.toString(empat));

        }
        txtpuntajs.setText(Integer.toString(puntajes));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_juego, menu);
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
