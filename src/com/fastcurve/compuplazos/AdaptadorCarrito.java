package com.fastcurve.compuplazos;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
 
public class AdaptadorCarrito extends ArrayAdapter<Computadora> {
	private final Context context;
	private final ArrayList<Computadora> values;
	DecimalFormat df = new DecimalFormat("#.##");
 
	public AdaptadorCarrito(Context context, ArrayList<Computadora> lista) {
		super(context, R.layout.carrito, lista);
		this.context = context;
		this.values = lista;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.carrito, parent, false);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.ImageView01);
			TextView textView = (TextView) rowView.findViewById(R.id.TextView01);
			TextView textView2 =(TextView) rowView.findViewById(R.id.TextView08);
			EditText cantidad = (EditText)rowView.findViewById(R.id.Cantidad);
			if(values.get(position).getCantidad()>=1) {
			textView.setText("Marca: "+values.get(position).getMarca());
			textView2.setText("Precio: "+values.get(position).getPrecio());

		//textView2.setText("Precio: "+df.format(values2.get(position)));
		}else{
			rowView.setVisibility(View.GONE);
		}
		return rowView;
		
	}
}