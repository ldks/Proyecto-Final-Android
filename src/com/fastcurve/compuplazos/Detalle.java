package com.fastcurve.compuplazos;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Detalle extends Activity{

	TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detallada);
		Intent intent = getIntent();
		String marca= intent.getStringExtra("Marca");
		String detalles = intent.getStringExtra("Detalles");
		ArrayList<Computadora> lista = intent.getBundleExtra("bundle").getParcelableArrayList("lista");
		Log.i("Lista", lista.get(0).getClave());
		text = (TextView)findViewById(R.id.TextView04);
		text.setText(detalles);
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
