package com.example.sw_josemanuelcanete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sw_josemanuelcanete.Adapter.PersonajeAdapter
import com.example.sw_josemanuelcanete.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.listaPersonajes.layoutManager = LinearLayoutManager(this)
        // Creamos una lista vacia de la clase Personaje Adapter
        val personajeAdapter = PersonajeAdapter(emptyList())
        binding.listaPersonajes.adapter = personajeAdapter

        // Asignamos instrucciones cuando pulsemos el boton de ver listado
        binding.BVer.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {

                // Asigamos a una variable la web de donde debe recibir la informacion del personaje
                val call: Response<PersonajeResponse> = getRetrofit().create(APIService::class.java).getPersonaje("people/")
                val personaje = call.body()
                runOnUiThread{
                    if (call.isSuccessful){
                        // Si la llamada ha salido bien creamos una lista vacia
                        val resultado = personaje?.results ?: emptyList()

                        // Y aqui le añadimos los personajes
                        personajeAdapter.listaPersonajes = resultado
                        personajeAdapter.notifyDataSetChanged()

                    }
                    else{
                        // Aqui sacamos un cuadro de dialogo en caso de que la llamada no haya sido correcta
                        Toast
                            .makeText(this@MainActivity, "Ha ocurrido un error", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        // Aqui asignamos el cuadro de dialogo al boton de guardar listado
        binding.BGuardar.setOnClickListener {
            AlertDialog.Builder(this)
                // Aqui asignamos el titulo
                .setTitle("Guardar")
                // Aqui asignamos el mensaje
                .setMessage("¿Desea guardar el listado?")
                // Aqui indicamos que solo se podra cerrar en caso de pulsar alguno de los dos botones
                .setCancelable(false)
                // Mensaje en caso de pulsar "OK"
                .setPositiveButton(android.R.string.ok, { dialog, which -> Toast.makeText(applicationContext, "El listado se ha guardado.", Toast.LENGTH_SHORT).show() })
                // Mensaje en caso de pulsar "Cancel"
                .setNegativeButton(android.R.string.cancel,  { dialog, which -> Toast.makeText(applicationContext, "Ha cancelado la accion.", Toast.LENGTH_SHORT).show() })
                // Aqui mostramos el cuadro de dialogo
                .show()


        }

    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}