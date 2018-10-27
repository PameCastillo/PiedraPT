package com.example.toshiba.pierdapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdaptadorPersonalizado extends BaseAdapter {
    //Declaracion de variables
    public static Puntaje[] puntajes = new Puntaje[3];
    private Context contextoApp;

    public AdaptadorPersonalizado(Context contexto)
    {
        this.contextoApp = contexto;
    }

    @Override
    public int getCount() {
       return this.puntajes.length;
    }

    @Override
    public Object getItem(int position) {
        return puntajes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Validamos que la vista es vacia
        if(convertView == null)
        {
            LayoutInflater creadorLayout = (LayoutInflater)this.contextoApp.getSystemService(this.contextoApp.LAYOUT_INFLATER_SERVICE);
            convertView = creadorLayout.inflate(R.layout.plantilla_jugadores, null);
            TextView lblnombre = (TextView)convertView.findViewById(R.id.lblnombre);
            TextView lblpuntaje = (TextView)convertView.findViewById(R.id.lblpuntaje);

            if(puntajes[position] != null)
            {
                //Creamos los textview
                Puntaje jugadorItem = puntajes[position];
                lblnombre.setText(jugadorItem.getNombre());
                lblpuntaje.setText(Integer.toString(jugadorItem.getPuntaje()));
            }
            else
            {
                lblnombre.setText("");
                lblpuntaje.setText("");
            }
        }
        return convertView;
    }
}
