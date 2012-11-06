package com.fastcurve.compuplazos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class Adaptador extends ArrayAdapter<Computadora> {
	private final Context context;
	private final Computadora[] values;
 
	public Adaptador(Context context, Computadora[] values) {
		super(context, R.layout.activity_main, values);
		this.context = context;
		this.values = values;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.activity_main, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values[position].getMarca());
 
		// Change icon based on name
		//String s = values[position].getImagen();
		String s="lol";
		System.out.println(s);
 
		if (s.equals("HP")) {
			imageView.setImageResource(R.drawable.lap2);
		} else if (s.equals("Compaq")) {
			imageView.setImageResource(R.drawable.lap2);
		} else if (s.equals("Acer")) {
			imageView.setImageResource(R.drawable.lap2);
		} else {
			imageView.setImageResource(R.drawable.lap2);
		}
 
		return rowView;
	}
}