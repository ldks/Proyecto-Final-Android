package com.fastcurve.compuplazos;

import java.io.*;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	
	// Tarea que actualiza la lista de computadoras
	private final ActualizaLista task = new ActualizaLista();
	
	/*
	 * La variable currentView sirve de "singleton" para saber en que vista se
	 * habia quedado la pantalla antes de girarla de este modo, cada ves que
	 * cambie la pantalla, puede volver a cargar la vista donde se encontraba en
	 * lugar de la de default.
	 */
	private static int currentView;
	
	// lista de computadoras
	private ArrayList<Computadora> lista = new ArrayList<Computadora>();
	
	// dialogo mostrado al actualizar la lista de computadoras
	private ProgressDialog progressDialog;

	// Dialogo para confirmar cerrar la aplicación
	private AtomicBoolean dialogoVisible = new AtomicBoolean();

	// Este objeto sirve para poder abrir un archivo de audio y poder
	// manipularlo
	MediaPlayer mPlayer;
	
	/*
	 * Metodo para probar autogeneraci�n de tabla
	 */
	static final int cantidad=5;
	static final Computadora lista2[]=new Computadora[cantidad];
	public void creaLista(){
		for(int i=0; i<cantidad;i++){
			lista2[i]=new Computadora();
		}
	}
	/*
	 * fin de prueba
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

		// inicializamos el dialogo que se muestra al actualizar
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Actualizando...");
		progressDialog.setTitle("Progreso");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setCancelable(false);

		// crea el archivo de audio y lo reproduce
		mPlayer = MediaPlayer.create(this, R.raw.dviolin);
		mPlayer.setLooping(true);
		mPlayer.start();
		mPlayer.stop();
		
		actualizarLista();
		
		creaLista();
		//setListAdapter(new Adaptador(this,	lista));
	
	}

	private void actualizarLista() {
		progressDialog.show();
		task.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * Este metodo es llamado cuando se selecciona una de las
	 * opciones(non-Javadoc)
	 * 
	 * @param item
	 *            es el objeto que contiene que boton de las opciones ha sido
	 *            presionado
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Este switch sirve para ejecutar un metodo acorde a la opcion que se
		// seleccionó
		switch (item.getItemId()) {
		case R.id.actualizarMenuItem:
			this.actualizarLista();
			break;
		case R.id.principalMenuItem:
			currentView = R.layout.activity_main;
			break;
		case R.id.empresaMenuItem:
			currentView = R.layout.acercade_empresa;
			break;
		case R.id.nosotrosMenuItem:
			currentView = R.layout.acercade_nosotros;
			break;
		case R.id.aplicacionMenuItem:
			currentView = R.layout.acercade_aplicacion;
			break;
		case R.id.salirMenuItem:
			destroy();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		setContentView(currentView);
		return true;
	}

	/**
	 * Este metodo es llamado cuando la pantalla gira y/o cambia de
	 * tamaño(non-Javadoc) En el manifest se agrego la siguiente linea
	 * "android:configChanges="orientation|screenSize" la cual este metodo
	 * captura cada ves que la pantalla cambia
	 * 
	 * @see android.app.Activity#onConfigurationChanged(android.content.res.Configuration)
	 */
	/*public void onConfigurationChanged(Configuration newConfig) {
	 *
	 *
		super.onConfigurationChanged(newConfig);
		// cambia el view a lo que estaba antes de cambiar la orientación
		setContentView(currentView);
	}
	*/
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
    	//get selected items
    	int pos= (int)getListAdapter().getItemId(position);
		String selectedValue = lista2[(int)getListAdapter().getItemId(position)].getMarca();
		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
		//carga la activity de detallada
		Intent nextActivity = new Intent(this, Detalle.class);
		nextActivity.putExtra("Marca", lista.get(pos).getMarca());
		nextActivity.putExtra("Detalles", lista.get(pos).getDetalles());
		startActivity(nextActivity);
 
	}

	/**
	 * Para evitar que el audio siga reproduciendose una ves cerrada la
	 * aplicacion, se usa este metodo, que al precionar back, primero libera los
	 * recursos de audio y despues hace lo que el boton back deberia de hacer.
	 */
	public void onBackPressed() {
		mPlayer.release();
		this.finish();
	}

	/**
	 * Muestra un dialogo para confirmar si se desea cerrar la aplicación
	 */
	public void destroy() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setMessage("¿Realmente quiere salir?");
		builder.setCancelable(true);
		builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialogoVisible.set(false);
				// matar la aplicación
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialogoVisible.set(false);
				// cancelar dialogo
				dialog.cancel();
			}
		});
		dialogoVisible.set(true);
		builder.show();
	}
	
	/**
	 * Tarea que se ejecuta en el fondo y actualiza la lista de computadoras
	 */
	public class ActualizaLista extends AsyncTask<Void, String, String> {
		final String URL = "http://200.52.163.45/android_connector.aspx/";

		/**
		 * proceso que hace la llamada a la base de datos
		 */
		@Override
		protected String doInBackground(Void... params) {
			HttpClient httpclient = new DefaultHttpClient();
			String result = "";
			try {
				HttpGet httpget = new HttpGet(URL);
				Log.i("Background Task", "executing request " + httpget.getURI());

				// Create a response handler
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				result = httpclient.execute(httpget, responseHandler);
			} catch (ClientProtocolException e) {
				Log.e("ClientProtocolException:", e.getLocalizedMessage());
			} catch (IOException e) {
				Log.e("IOException:", e.getLocalizedMessage());
			} finally {
				// When HttpClient instance is no longer needed,
				// shut down the connection manager to ensure
				// immediate deallocation of all system resources
				httpclient.getConnectionManager().shutdown();
			}
			return result;
		}

		/**
		 * actualiza la lista de computadoras con los resultados
		 */
		@Override
		protected void onPostExecute(String result) {
			progressDialog.dismiss();
			JSONArray computadoras = null;
			try {
				computadoras = new JSONArray(result);
				
				lista.clear();
				for (int i=0; i<computadoras.length(); i++) {
					Computadora computadora = new Computadora();
					JSONObject json = computadoras.getJSONObject(i);
					computadora.setId(json.getInt("Id"));
					computadora.setClave(json.getString("Clave"));
//					computadora.setImagen(jsonarrayToImage(json.getJSONArray("Imagen")));
					computadora.setCategoria(json.getString("Categoria"));
					computadora.setMarca(json.getString("Familia"));
					computadora.setModelo(json.getString("Sub familia"));
					computadora.setPrecio(json.getDouble("Precio"));
					computadora.setDetalles(json.getString("Detalles"));
					lista.add(computadora);
				}
			} catch (JSONException e) {
				Log.e("JSON Error", "Error converting results to JSON object");
				Log.e("JSON Error", e.getLocalizedMessage());
				return;
			}
			Log.i("Actualizacion:: ", computadoras.toString());
			System.out.println("Prueba de la lista: "+lista.get(0).getDetalles());
			setListAdapter(new Adaptador(MainActivity.this,	lista));
			
		}
		
		/**
		 * Convierte un arreglo JSON de ints a una imagen
		 * @param array arreglo de bytes que representa la imagen
		 * @return la imagen que resulta del arreglo, null si array es null
		 * @throws JSONException
		 */
		private Bitmap jsonarrayToImage(JSONArray array) throws JSONException {
			if (array == null) return null;
			byte[] bitmapdata = new byte[array.length()];
			for (int i=0; i<bitmapdata.length; i++) {
				bitmapdata[i] = (byte) array.getInt(i);
			}
			return BitmapFactory.decodeByteArray(bitmapdata , 0, bitmapdata.length);
		}
		
	}
	public void ClickHandler(View v){
    	System.out.println("Lol");
    }
}