package com.fastcurve.compuplazos;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Carrito extends ListActivity{
	TextView text;
	String marca;
	String detalles;
	double precio;
	private ArrayList<Computadora> lista = new ArrayList<Computadora>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carrito);
		Intent intent = getIntent();
		String marca= intent.getStringExtra("Marca");
		String detalles = intent.getStringExtra("Detalles");
		text = (TextView)findViewById(R.id.TextView04);
		text.setText(detalles);
		setListAdapter(new AdaptadorCarrito(this,lista));
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

}
