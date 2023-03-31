package com.example.apiaccountsclient

import android.os.Bundle
import android.widget.LinearLayout
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.apiaccountsclient.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val AdapterCuentas : AdapterCuentas = AdapterCuentas()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarListasCuentas()
    }

    fun cargarListasCuentas(){
        val url = "https://dog.ceo/api/breeds/list/all"
        val solicitud = JsonObjectRequest(Request.Method.GET, url, null,
            {response->
                try{
                    if(response.getString("status")=="200 OK"){
                        Toast.makeText(this, "Descarga Exitosa", Toast.LENGTH_SHORT).show()
                        val array_datos=response.getJSONObject("message")
                        val items:MutableList<listadoPrin> = ArrayList()

                        val clave = array_datos.names()
                        for (i in 0 until clave.length()){
                            val valor = clave.getString(i)
                            items.add(listadoPrin(valor))
                        }
                        binding.rvCuentas.setHasFixedSize(true)
                        binding.lbltitulo.text = "Mostrando "+clave.length().toString()+ " Cuentas"
                        binding.rvCuentas.layoutManager = LinearLayoutManager(this)
                        AdapterCuentas.AdapterCuentas(items, this)
                        binding.rvCuentas.adapter = AdapterCuentas

                    } else {
                        Toast.makeText(this, "Error al descargar", Toast.LENGTH_SHORT).show()
                    }
                } catch (e:Exception){
                    Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                }
            },{
                Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            })

        val cola = Volley.newRequestQueue(this)
        cola.add(solicitud)
    }
}