package com.fastcurve.compuplazos;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
 
public class Adaptador extends ArrayAdapter<Computadora> {
	private final Context context;
	private final ArrayList<Computadora> values;
	DecimalFormat df = new DecimalFormat("#.##");
 
	public Adaptador(Context context, ArrayList<Computadora> lista) {
		super(context, R.layout.activity_main, lista);
		this.context = context;
		this.values = lista;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.activity_main, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.marca);
		TextView modelo = (TextView) rowView.findViewById(R.id.modelo);
		TextView textView2 = (TextView) rowView.findViewById(R.id.precio);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText("Marca: "+values.get(position).getMarca());
		modelo.setText("Modelo: "+values.get(position).getModelo());
		textView2.setText("Precio: "+df.format(values.get(position).getPrecio()));
		
		final int pos = position;
		Button btnAgregarACarrito = (Button) rowView.findViewById(R.id.boton);
		btnAgregarACarrito.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	int cantidad = MainActivity.lista.get(pos).getCantidad()+1;
                MainActivity.lista.get(pos).setCantidad(cantidad);
                Intent nextActivity = new Intent(context, Carrito.class);
        		
                context.startActivity(nextActivity);
            }
        });
		// Change icon based on name
		//String s = values[position].getImagen();
		String s="lol";
		if (s.equals("HP")) {
			imageView.setImageResource(R.drawable.lap2);
		} else if (s.equals("Compaq")) {
			imageView.setImageResource(R.drawable.lap2);
		} else if (s.equals("Acer")) {
			imageView.setImageResource(R.drawable.lap2);
		} else {
			
		}
 
		return rowView;
	}
}