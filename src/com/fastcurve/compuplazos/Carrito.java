package com.fastcurve.compuplazos;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Carrito extends ListActivity{
	private final ArrayList<Computadora> carrito = new ArrayList<Computadora>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		for (Computadora compu : MainActivity.lista) {
			if (compu.getCantidad()>0) carrito.add(compu);
			if (compu.getCantidad()==0) carrito.remove(compu);
		}
		setListAdapter(new AdaptadorCarrito(this,carrito));
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
	

	public void comprar(View v) {
		Intent nextActivity = new Intent(this, Compra.class);
		startActivity(nextActivity);
	}

}
