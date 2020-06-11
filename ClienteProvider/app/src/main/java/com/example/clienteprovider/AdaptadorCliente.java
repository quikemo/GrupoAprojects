package com.example.clienteprovider;

//Aplicaci�n Android que implementa la clase ContentResolver,
//para enviar solicitudes al Content Provider definido, y mostrar los datos
//en un componente de tipo ListView.
//
//academiaandroid.com
//
//by Jos� Antonio G�zquez Rodr�guez


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/*Clase AdaptadorCliente, que hereda de la clase base BaseAdapter, encargada de 
construir la vista de cada uno de los �tems que se mostrar�n en el componente ListView.*/
public class AdaptadorCliente extends BaseAdapter{

	/*Se declaran las clases necesarias para posteriormente a�adirlas como argumentos al constructor.*/
	private Context context;	
	private ArrayList<Cliente> cliente;
	private LayoutInflater inflater;

	/*Constructor que ser� invocados desde el componente ListView (como argumento del metodo setAdapter())
	, y que recibe el contexto de la aplicaci�n y los objetos almacenados en un ArrayList.*/
	public AdaptadorCliente(Context context, ArrayList<Cliente> cliente) 
	{	
		this.context = context;		
		this.cliente = cliente;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return cliente.size();
	}

	@Override
	public Object getItem(int position) {
		return cliente.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup parent) {
		
		View v = convertview;
		ViewHolder vista = new ViewHolder();
				
		if(convertview == null)
		{	
			v = inflater.inflate(R.layout.activity_adaptador_cliente, parent, false);
			
			/*Se asocian los controles de tipo TextView con sus recursos a nivel de layout.*/
			vista.txtId = (TextView)v.findViewById(R.id.txtId);
			vista.txtCliente = (TextView)v.findViewById(R.id.txtCliente);
			vista.txtTelefono = (TextView)v.findViewById(R.id.txtTelefono);
			vista.txtDescripcion = (TextView)v.findViewById(R.id.txtDescripcion);
			/*Se asignan a cada componente el �ndice de la columna a mostrar, a partir de los resultados 
			de la consulta procesada. En cada �tem del componente de selecci�n ListView se mostrar�n 
			los registros de id, cliente y tel�fono almacenado.*/	
			vista.txtId.setText(" Id: " + cliente.get(position).getId() + "  ");
			vista.txtCliente.setText("Cliente: " + cliente.get(position).getCliente() + " ");
			vista.txtTelefono.setText("Telefono: " + cliente.get(position).getTelefono() + "  ");
			vista.txtDescripcion.setText("Descripcion " +cliente.get(position).getDescripcion());
		}
		else
		{
			vista = (ViewHolder) v.getTag();
		}					
	return v;
	}

	static class ViewHolder {
		
		public TextView txtId, txtCliente, txtTelefono, txtDescripcion;
	}
}


