package com.fastcurve.compuplazos;

import android.graphics.Bitmap;



public class Computadora {
	private int id;
	private String clave;
	private Bitmap imagen;
	private String categoria;
	private String marca;
	private String modelo;
	private String detalles;
	private double precio;
	
	public Computadora(){
		setId(-1);
		setClave("");
		imagen=null;
		setCategoria("");
		marca="";
		setModelo("");
		detalles="";
		precio=0;
	}
	
	public Computadora (int id, String cl, Bitmap i, String ca, String ma, String mo, String d, float p) {
		this.setId(id);
		this.setClave(cl);
		this.setImagen(i);
		this.setCategoria(ca);
		this.setMarca(ma);
		this.setModelo(mo);
		this.setDetalles(d);
		this.setPrecio(p);
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
}
