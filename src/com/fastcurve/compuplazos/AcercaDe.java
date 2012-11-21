package com.fastcurve.compuplazos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AcercaDe extends Activity {
	
	public static final int ACERCA_DE_EMPRESA = 0;
	public static final int ACERCA_DE_APLICACION = 1;
	public static final int ACERCA_DE_NOSOTROS = 2;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		int layout = intent.getIntExtra("layout", R.layout.acercade_nosotros);
		switch (layout) {
		case ACERCA_DE_EMPRESA:
			setContentView(R.layout.acercade_empresa);
			break;
		case ACERCA_DE_APLICACION:
			setContentView(R.layout.acercade_aplicacion);
			break;
		case ACERCA_DE_NOSOTROS:
			setContentView(R.layout.acercade_nosotros);
			break;
		default:
			setContentView(R.layout.acercade_nosotros);
			break;
		}
	}
}
