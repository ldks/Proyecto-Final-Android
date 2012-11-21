package com.fastcurve.compuplazos;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Detalle extends Activity{

	Computadora computadora;
	ArrayList<Computadora> lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detallada);
		Intent intent = getIntent();
		int pos = intent.getIntExtra("pos", 0);
		lista = intent.getBundleExtra("bundle").getParcelableArrayList("lista");
		
		this.computadora = lista.get(pos);
		String marca = computadora.getMarca();
		String modelo = computadora.getModelo();
		double precio = computadora.getPrecio();
		String detalles = computadora.getDetalles();
		
		TextView textViewMarca = (TextView) findViewById(R.id.marca);
		TextView textViewModelo = (TextView) findViewById(R.id.modelo);
		TextView textViewPrecio = (TextView) findViewById(R.id.precio);
		TextView textViewDetalles =  (TextView) findViewById(R.id.textViewDetalles);
		
		textViewMarca.setText("Marca: "+marca);
		textViewModelo.setText("Modelo: "+modelo);
		textViewPrecio.setText("Precio: "+precio);
		textViewDetalles.setText(detalles);
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
		nextActivity.putExtra("lista", lista);
		startActivity(nextActivity);
	}

}
