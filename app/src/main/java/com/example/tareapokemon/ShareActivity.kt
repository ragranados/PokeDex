package com.example.tareapokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.tareapokemon.models.Pokemon
import kotlinx.android.synthetic.main.activity_share.*
import kotlinx.android.synthetic.main.pokemon_list_item.*
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class ShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        var pokemonInfo: Pokemon = intent?.extras?.getParcelable("Pokemon")?: Pokemon()

        initActivity(pokemonInfo)
    }

    fun initActivity(pokemon: Pokemon){
        Log.d("prueba:v", "si es esta ${pokemon.name}")

        Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png")
                .into(this.image)
        share_pokemon_weight.text = pokemon.name
    }


}
