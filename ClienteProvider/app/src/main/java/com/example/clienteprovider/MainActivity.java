package com.example.clienteprovider;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.libreriacarteracliente.CompartirDatos;
import com.example.libreriacarteracliente.EstructuraDatos;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    /*Se declaran los componentes ListView y TextView de la interfaz de usuario.*/
    private ListView listaClientes;
    private Cursor c;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Se enlazan los componentes con sus recursos a nivel de layout.*/
        listaClientes = (ListView)findViewById(android.R.id.list);


        listaClientes.setAdapter(new AdaptadorCliente(this, mostrarRegistrosAplicacion()));
        /*Evento lanzado al realizar click sobre uno de los item de la lista.*/
        listaClientes.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String idCliente = "Sin datos";

                if(c.moveToPosition(position))
                {
					/*Al seleccionar un item de la lista, se mostrara la descripcion del cliente seleccionado,
					en el componente TextView definido.*/
                    idCliente = c.getString(c.getColumnIndex(EstructuraDatos._ID));

                }
                Toast.makeText(MainActivity.this, "Id cliente seleccionado: " + idCliente, Toast.LENGTH_LONG).show();
            }
        });
    }

    /*Metodo que permite consultar la tabla NuevosClientes, almacenando los resultados
    en un objeto Cursor. Este metodo devuelve un ArrayList de objetos Cliente, con todos los registros almacenados.*/
    public ArrayList<Cliente> mostrarRegistrosAplicacion()
    {
        ArrayList<Cliente> objetoCliente = new ArrayList<Cliente>();
		/*Se declara e inicializa la clase ContentResolver, referenciandole el metodo getContentResolver(), que nos permitira
		acceder a los metodos sobrescritos en la clase que hereda de la clase base ContentProvider*/
        ContentResolver resolver = getContentResolver();
        Cliente cliente;
        try{
            c = resolver.query(CompartirDatos.CONTENT_URI, CompartirDatos.columnas, null, null, null);
            if(c != null)
            {
                if(c.moveToFirst())
                {
                    Log.i("MainActivity", "Si devuelve cursor");
                    do
                    {
                        cliente = new Cliente(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
                        objetoCliente.add(cliente);

                    }while(c.moveToNext());
                }
            }else
            {
                Log.i("MainActivity", "No devuelve cursor");
                Toast.makeText(this, "El objeto cursor esta vacio.", Toast.LENGTH_LONG).show();
            }
        }catch(Exception ex)
        {
            ex.getMessage();
        }
        return objetoCliente;
    }

    /*Evento onClick() encargado de invocar al metodo delete(), para eliminar el registro seleccionado,
    a partir del campo _id del cliente.*/
    public void eliminarRegistro(View view)
    {
        ContentResolver resolver = getContentResolver();
        String id_cliente = c.getString(c.getColumnIndex(com.example.libreriacarteracliente.EstructuraDatos._ID));
        resolver.delete(CompartirDatos.CONTENT_URI, EstructuraDatos._ID + "=" + id_cliente, null);
        Toast.makeText(MainActivity.this, "Id cliente eliminado: " + id_cliente, Toast.LENGTH_LONG).show();
        onCreate(bundle);
    }

    public void refrescarPantalla(View view){
        onCreate(bundle);
    }
}