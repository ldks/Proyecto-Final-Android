package com.fastcurve.compuplazos;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Detalle extends Activity{

	TextView text;
	String marca;
	String detalles;
	double precio;
	ArrayList<String> carroM;
	ArrayList<String> carroP;
	ArrayList<Computadora> lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detallada);
		Intent intent = getIntent();
		String marca= intent.getStringExtra("Marca");
		String detalles = intent.getStringExtra("Detalles");
		text = (TextView)findViewById(R.id.TextView04);
		text.setText(detalles);
		double precio=intent.getDoubleExtra("precio", 0);
		carroM=intent.getStringArrayListExtra("lMarca");
		carroP=intent.getStringArrayListExtra("lPrecio");
		
		lista = intent.getBundleExtra("bundle").getParcelableArrayList("lista");
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
	public void clickHandler(View v){
		Intent nextActivity = new Intent(this, Carrito.class);
		carroM.add(marca);
		carroP.add(""+precio);
		nextActivity.putExtra("lista", lista);
		startActivity(nextActivity);
	}

}
