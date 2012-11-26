package com.fastcurve.compuplazos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fastcurve.compuplazos.Mail;

public class Compra extends Activity {

	private EditText editTextEmail;
	private EditText editTextTel;
	private TextView textViewError;
	private Button btnPedir;
	private String pedido;
	private boolean mandando = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compra);
		Intent intent = getIntent();
		// pedido = intent.getExtras().getString("claves", "Compra vacía");
		pedido = "Compra vacia";
		editTextEmail = (EditText) findViewById(R.id.editTextEmail);
		editTextTel = (EditText) findViewById(R.id.editTextTel);
		textViewError = (TextView) findViewById(R.id.textViewError);
		btnPedir = (Button) findViewById(R.id.btnPedir);
		btnPedir.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				pedirCotizacion();
			}
		});
	}

	/** Manda una cotización a Compuplazos por mail */
	public void pedirCotizacion() {
		// para evitar que se manden multiples al picarle mucho al boton
		if (!mandando) {
			mandando = true;
			String email = editTextEmail.getText().toString();
			String tel = editTextTel.getText().toString();

			// checa si almenos uno de los dos campos tiene algo
			if (email.isEmpty() && tel.isEmpty()) {
				textViewError
						.setText("Se debe de llenar por lo menos uno de los campos");
			} else {
				textViewError.setText("");

				Mail mail = new Mail("android.compuplazos@gmail.com",
						"androidplazos31");
				String body = "Un cliente con esta información:\n" + "	Email: "
						+ email + "\n" + "	Teléfono: " + tel + "\n"
						+ "Pidió a traves de la aplicación en android:\n"
						+ pedido;

				mail.setTo(new String[] { "islarrab@gmail.com" });
				mail.setFrom("android.compuplazos@gmail.com");
				mail.setSubject("Compra por aplicación de android");
				mail.setBody(body);

				try {
					if (mail.send()) {
						Toast.makeText(getApplicationContext(),
								"Mail mandado exitosamente", Toast.LENGTH_LONG)
								.show();
					} else {
						Toast.makeText(getApplicationContext(),
								"Error al mandar el mail", Toast.LENGTH_LONG)
								.show();
					}
				} catch (Exception e) {
					Log.e("MailError",
							"Error al mandar el mail: "
									+ e.getLocalizedMessage());
				}
				finish();
			}
		}

	}
}
