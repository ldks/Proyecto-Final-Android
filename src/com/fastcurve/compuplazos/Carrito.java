package com.fastcurve.compuplazos;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Carrito extends ListActivity{
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
		//setContentView(R.layout.carrito);
		Intent intent = getIntent();
		carroM=intent.getStringArrayListExtra("lMarca");
		carroP=intent.getStringArrayListExtra("lPrecio");
		lista = intent.getBundleExtra("bundle").getParcelableArrayList("lista");
		setListAdapter(new AdaptadorCarrito(this,lista));
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	

}
