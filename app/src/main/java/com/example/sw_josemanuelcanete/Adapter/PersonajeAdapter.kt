package com.example.sw_josemanuelcanete.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sw_josemanuelcanete.R

// Pasamos por parametros la lista de personajes de tipo clase Result y devuelve el recyclerview de tipo clase Personaje View Holder
class PersonajeAdapter(var listaPersonajes:List<com.example.sw_josemanuelcanete.Result>): RecyclerView.Adapter<PersonajeViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonajeViewHolder(layoutInflater.inflate(R.layout.item_personaje, parent, false))
    }

    // Asignamos el personaje a la posicion indicada en el recycler
    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val item = listaPersonajes[position]
        holder.render(item)
    }

    // Contamos los personajes que va a haber en el recycler
    override fun getItemCount(): Int = listaPersonajes.size

}