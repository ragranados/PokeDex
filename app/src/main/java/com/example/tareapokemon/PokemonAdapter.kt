package com.example.tareapokemon

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tareapokemon.models.Pokemon
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class PokemonAdapter(val items: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_item,parent,false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, count: Int) {
        holder.bind(items[count])

        var countUno:Int = count + 1

        Glide.with(holder.itemView.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$countUno.png")
            .into(holder.itemView.pokemon_icon)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(item: Pokemon) = with(itemView){
            pokemon_name.text = item.name
        }
    }
}