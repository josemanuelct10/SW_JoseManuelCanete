package com.example.sw_josemanuelcanete.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sw_josemanuelcanete.databinding.ItemPersonajeBinding

class PersonajeViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ItemPersonajeBinding.bind(view)
    fun render(personajeModel: com.example.sw_josemanuelcanete.Result){

        // Asignamos al text view el nombre del personaje
        binding.nombrePersonaje.text = personajeModel.name

        // Asignamos al text view la altura del personaje
        binding.alturaPersonaje.text = personajeModel.height

        // Asignamos al text viel el numero de peliculas contando los elementos del array peliculas y pasandolo como cadena de texto
        binding.peliculasPersonaje.text = personajeModel.films.size.toString()


    }
}