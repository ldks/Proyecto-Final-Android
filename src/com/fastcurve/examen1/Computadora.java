package com.fastcurve.examen1;


public class Computadora {
	String imagen;
	String marca;
	String modelo;
	String memoria;
	String discoDuro;
	String pantalla;
	int precio;
	
	public Computadora(){
		imagen="prueba.jpg";
		marca="Generica";
		modelo="007";
		discoDuro="N/A";
		pantalla="N/A";
		precio=10;
	}
	
	public Computadora (String imagen, String marca, String modelo, String discoDuro, String pantalla, int precio){
		this.imagen=imagen;
		this.marca=marca;
		this.modelo=modelo;
		this.discoDuro=discoDuro;
		this.pantalla=pantalla;
		this.precio=precio;
	}	
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
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

	public String getMemoria() {
		return memoria;
	}

	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}

	public String getDiscoDuro() {
		return discoDuro;
	}

	public void setDiscoDuro(String discoDuro) {
		this.discoDuro = discoDuro;
	}

	public String getPantalla() {
		return pantalla;
	}

	public void setPantalla(String pantalla) {
		this.pantalla = pantalla;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

}
