package com.fastcurve.compuplazos;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
 
public class AdaptadorCarrito extends ArrayAdapter<Computadora> {
	private final static int layout = R.layout.carrito_list;
	private final Context context;
	private final ArrayList<Computadora> values;
	DecimalFormat df = new DecimalFormat("$#,###.##");
	
	public AdaptadorCarrito(Context context, ArrayList<Computadora> lista) {
		super(context, layout, lista);
		this.context = context;
		this.values = lista;
	}
 
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(layout, parent, false);
		
		TextView tvMarca = (TextView) rowView.findViewById(R.id.textViewMarca);
		TextView tvModelo = (TextView) rowView.findViewById(R.id.textViewModelo);
		TextView tvPrecio = (TextView) rowView.findViewById(R.id.textViewPrecio);
		EditText etCantidad = (EditText) rowView.findViewById(R.id.etCantidad);
//		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		
		tvMarca.setText("Marca: "+values.get(position).getMarca());
		tvModelo.setText("Modelo: "+values.get(position).getModelo());
		tvPrecio.setText("Precio: "+df.format(values.get(position).getPrecio()));
		etCantidad.setText(""+values.get(position).getCantidad());
		
		ImageButton btnQuitar = (ImageButton)rowView.findViewById(R.id.btnQuitar);
		
		btnQuitar.setOnClickListener( new OnClickListener(){
			public void onClick(View v) {
				MainActivity.lista.get(position).setCantidad(0);
			}
		});		
		return rowView;
	}
}