package com.fastcurve.compuplazos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Compra extends Activity {
	
	private EditText editTextEmail;
	private EditText editTextTel;
	private TextView textViewError;
	private Button btnPedir;
	private String pedido;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compra);
		Intent intent = getIntent();
		editTextEmail = (EditText)findViewById(R.id.editTextEmail);
		editTextTel = (EditText)findViewById(R.id.editTextTel);
		textViewError = (TextView)findViewById(R.id.textViewError);
		btnPedir = (Button)findViewById(R.id.btnPedir);
		btnPedir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pedirCotizacion();
            }
        });
	}
	
	/** Manda una cotización a Compuplazos por mail*/
	public void pedirCotizacion() {
		Log.i("Compra click", "Click a Pedir Cotización");
		String email = editTextEmail.getText().toString();
		String tel = editTextTel.getText().toString();
		String text = "Un cliente con esta información:\n"+
					"	Email: "+email+"\n"+
					"	Teléfono: "+tel+"\n"+
					"Pidió a traves de la aplicación en android:\n";
		text.concat(pedido);
		if (email.isEmpty() && tel.isEmpty()) {
			textViewError.setText("Se debe de llenar por lo menos uno de los campos");
		} else {
			textViewError.setText("");
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			String[] recipients = new String[]{"slarra@pysis.com.mx", "",};
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Compra por android");
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
			emailIntent.setType("text/plain");
			startActivity(Intent.createChooser(emailIntent, "Mandar petición por..."));
			finish();
		}
	}
}
