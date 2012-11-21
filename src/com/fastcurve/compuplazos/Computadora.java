package com.fastcurve.compuplazos;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Computadora implements Parcelable {
	private int id;
	private String clave;
	private Bitmap imagen;
	private String categoria;
	private String marca;
	private String modelo;
	private String detalles;
	private double precio;

	public Computadora() {
		setId(-1);
		setClave("");
		id = 1;
		clave = "nula";
		imagen = null;
		setCategoria("");
		categoria = "N/A";
		marca = "Generica";
		setModelo("");
		modelo = "N/A";
		detalles = "";
		precio = 0;
	}

	public Computadora(int id, String cl, Bitmap i, String ca, String ma,
			String mo, String d, float p) {
		this.setId(id);
		this.setClave(cl);
		this.setImagen(i);
		this.setCategoria(ca);
		this.setMarca(ma);
		this.setModelo(mo);
		this.setDetalles(d);
		this.setPrecio(p);
	}
	
	public Computadora(Parcel parcel) {
		setId(parcel.readInt());
		setClave(parcel.readString());
		setCategoria(parcel.readString());
		setMarca(parcel.readString());
		setModelo(parcel.readString());
		setDetalles(parcel.readString());
		setPrecio(parcel.readDouble());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Bitmap getImagen() {
		return imagen;
	}

	public void setImagen(Bitmap imagen) {
		this.imagen = imagen;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int describeContents() {
		// no tengo idea de que haga esto
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// escribe los
		dest.writeInt(id);
		dest.writeString(clave);
		// dest.writeByteArray(imagen);
		dest.writeString(categoria);
		dest.writeString(marca);
		dest.writeString(modelo);
		dest.writeString(detalles);
		dest.writeDouble(precio);
	}

	public static final Parcelable.Creator<Computadora> CREATOR = new Parcelable.Creator<Computadora>() {
		public Computadora createFromParcel(Parcel in) {
			return new Computadora(in);
		}

		public Computadora[] newArray(int size) {
			return new Computadora[size];
		}
	};
}
