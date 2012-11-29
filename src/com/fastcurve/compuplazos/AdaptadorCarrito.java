package com.fastcurve.compuplazos;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
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
		final EditText etCantidad = (EditText) rowView.findViewById(R.id.etCantidad);
		// ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);

		tvMarca.setText("Marca: " + values.get(position).getMarca());
		tvModelo.setText("Modelo: " + values.get(position).getModelo());
		tvPrecio.setText("Precio: "
				+ df.format(values.get(position).getPrecio()));
		etCantidad.setText("" + values.get(position).getCantidad());

		ImageButton btnQuitar = (ImageButton) rowView.findViewById(R.id.btnQuitar);
		
		etCantidad.addTextChangedListener(new TextWatcher(){

			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				String cant = etCantidad.getText().toString();
				for (int a = 0; a < MainActivity.lista.size(); a++) {
					if ((values.get(position).getId()) == (MainActivity.lista.get(a)
							.getId())) {
						MainActivity.lista.get(a).setCantidad(
								Integer.parseInt(cant));
					}
				}
				Intent intent = new Intent(context, Carrito.class);
				// intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				((Carrito) context).finish();
				context.startActivity(intent);
				
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
			
		});

		btnQuitar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				for (int a = 0; a < MainActivity.lista.size(); a++) {
					if ((values.get(position).getId()) == (MainActivity.lista.get(a)
							.getId())) {
						MainActivity.lista.get(a).setCantidad(0);
					}
				}
				Intent intent = new Intent(context, Carrito.class);
				// intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				((Carrito) context).finish();
				context.startActivity(intent);
			}

		});
		
		/*
		Button boton = (Button) rowView.findViewById(R.id.Guardar);
		boton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String cant = etCantidad.getText().toString();
				for (int a = 0; a < MainActivity.lista.size(); a++) {
					if ((values.get(pos).getId()) == (MainActivity.lista.get(a)
							.getId())) {
						MainActivity.lista.get(a).setCantidad(
								Integer.parseInt(cant));
					}
				}
				Intent intent = new Intent(context, Carrito.class);
				// intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				((Carrito) context).finish();
				context.startActivity(intent);
			}

		});
		*/
		
		
		/*
		Button boton3 = (Button) rowView.findViewById(R.id.Continuar);
		boton3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				((Carrito) context).finish();
			}
		});
		*/
		return rowView;
	}
}