package com.fastcurve.compuplazos;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
		
		TextView textView = (TextView) rowView.findViewById(R.id.TextView01);
		TextView textView2 =(TextView) rowView.findViewById(R.id.TextView08);
		EditText num1 = (EditText)rowView.findViewById(R.id.cantidad);
		textView.setText("Marca: "+values.get(position).getMarca());
		textView2.setText("Precio: "+df.format(values.get(position).getPrecio()));
		num1.setText(""+values.get(position).getCantidad());
		Button boton= (Button)rowView.findViewById(R.id.Guardar);
		Button boton2= (Button)rowView.findViewById(R.id.Quitar);
		Button boton3= (Button)rowView.findViewById(R.id.Continuar);
		final int pos=position; 
		final EditText num=num1;
		boton.setOnClickListener( new OnClickListener(){
			public void onClick(View v) {
				String cant=num.getText().toString();
				for(int a=0; a<MainActivity.lista.size();a++){
					if((values.get(pos).getId())==(MainActivity.lista.get(a).getId())){
						MainActivity.lista.get(a).setCantidad(Integer.parseInt(cant));
					}
				}
				Intent intent = new Intent(context, Carrito.class);
				//intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				((Carrito)context).finish();
				context.startActivity(intent);
			}
			
		});
		boton2.setOnClickListener( new OnClickListener(){
			public void onClick(View v) {
				for(int a=0; a<MainActivity.lista.size();a++){
					if((values.get(pos).getId())==(MainActivity.lista.get(a).getId())){
						MainActivity.lista.get(a).setCantidad(0);
					}
				}
				Intent intent = new Intent(context, Carrito.class);
				//intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				((Carrito)context).finish();
				context.startActivity(intent);
			}
			
		});
		boton3.setOnClickListener( new OnClickListener(){
			public void onClick(View v) {
				((Carrito)context).finish();
			}
			
		});		
		return rowView;
	}
}