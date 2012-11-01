package com.fastcurve.examen1;

import java.util.concurrent.atomic.AtomicBoolean;

import com.fastcurve.examen1.R;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	
	/* 
	 * La variable currentView sirve de "singleton" para saber en que vista se habia quedado la pantalla antes de girarla
	 * de este modo, cada ves que cambie la pantalla, puede volver a cargar la vista donde se encontraba
	 * en lugar de la de default.
	 */
	private static int currentView;
	public int cantidad=5;
	public Computadora lista[]=new Computadora[cantidad];
	public void creaLista(){
		for(int i=0; i<cantidad;i++){
			lista[i]=new Computadora();
		}
	}
	
	// Dialogo para confirmar cerrar la aplicación
	private AtomicBoolean dialogoVisible = new AtomicBoolean();
	
	// Este objeto sirve para poder abrir un archivo de audio y poder manipularlo
	MediaPlayer mPlayer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //crea el archivo de audio y lo reproduce
        mPlayer= MediaPlayer.create(this, R.raw.dviolin);
        mPlayer.setLooping(true);
        mPlayer.start();
        mPlayer.stop();
        creaLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /**
     * Este metodo es llamado cuando se selecciona una de las opciones(non-Javadoc)
     * @param item es el objeto que contiene que boton de las opciones ha sido presionado
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	//Este switch sirve para ejecutar un metodo acorde a la opcion que se seleccionó
    	switch(item.getItemId()){
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
     * Este metodo es llamado cuando la pantalla gira y/o cambia de tamaño(non-Javadoc)
     * En el manifest se agrego la siguiente linea "android:configChanges="orientation|screenSize" la cual este metodo
     * captura cada ves que la pantalla cambia
     * @see android.app.Activity#onConfigurationChanged(android.content.res.Configuration)
     */
    public void onConfigurationChanged(Configuration newConfig){
    	super.onConfigurationChanged(newConfig);
    	// cambia el view a lo que estaba antes de cambiar la orientación
    	setContentView(currentView);
    }
    
    /**
     * Para evitar que el audio siga reproduciendose una ves cerrada la aplicacion, se usa este metodo, que al precionar
     * back, primero libera los recursos de audio y despues hace lo que el boton back deberia de hacer. 
     */
    public void onBackPressed() {
        mPlayer.release();
        this.finish();
    }
    
    /**
     * Muestra un dialogo para confirmar si se desea cerrar la aplicación
     */
    public void destroy() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setMessage("¿Realmente quiere salir?");
		builder.setCancelable(true);
		builder.setPositiveButton("Si",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int id) {
						dialogoVisible.set(false);
						// matar la aplicación
						android.os.Process.killProcess(android.os.Process.myPid());
					}
				});
		builder.setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int id) {
						dialogoVisible.set(false);
						// cancelar dialogo
						dialog.cancel();
					}
				});
		dialogoVisible.set(true);
		builder.show();
	}
}