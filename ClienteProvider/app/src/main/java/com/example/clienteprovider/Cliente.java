package com.example.clienteprovider;

//Aplicaci�n Android que implementa la clase ContentResolver,
//para enviar solicitudes al Content Provider definido, y mostrar los datos
//en un componente de tipo ListView.
//
//academiaandroid.com
//
//by Jos� Antonio G�zquez Rodr�guez

public class Cliente {
	
	private String id;
	private String cliente;	
	private String telefono;
	private String descripcion;
	
	public Cliente(String id, String cliente, String telefono, String descripcion)
	{
		this.id = id;
		this.cliente = cliente;
		this.telefono = telefono;
		this.descripcion = descripcion;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}
	
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
