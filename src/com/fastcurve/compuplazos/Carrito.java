package com.fastcurve.compuplazos;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Carrito extends ListActivity{
	private final ArrayList<Computadora> carrito = new ArrayList<Computadora>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.carrito);
		
		for (Computadora compu : MainActivity.lista) {
			if (compu.getCantidad()>0) carrito.add(compu);
		}
		/*for(int a=0;a<carrito.size();a++){
			if(carrito.get(a).getCantidad()==0){
				carrito.remove(a);
			}
		}
		*/
		setListAdapter(new AdaptadorCarrito(this,carrito));
		
		TextView tvTotal = (TextView) this.findViewById(R.id.tvTotal);
		double total = 0;
		for (Computadora c : carrito) {
			total += c.getPrecio()*c.getCantidad();
		}
		tvTotal.setText("Total: "+MainActivity.DF.format(total));
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
