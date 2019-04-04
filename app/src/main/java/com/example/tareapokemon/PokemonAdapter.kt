package com.example.tareapokemon

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.tareapokemon.models.Pokemon
import com.example.tareapokemon.models.SetClick
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class PokemonAdapter(val items: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {


    var url: String = ""


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


        url = items[count].url

        var countUno:Int = count + 1

        Glide.with(holder.itemView.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$countUno.png")
            .into(holder.itemView.pokemon_icon)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        //var urlIn: String
        init{
            itemView.setOnClickListener(this)

        }
        override fun onClick(v: View?) {
            Log.d("prueba:v","https://pokeapi.co/api/v2/pokemon/${itemView.pokemon_name.text}/")
        }

        fun bind(item: Pokemon) = with(itemView){
            pokemon_name.text = item.name
        }

    }
}