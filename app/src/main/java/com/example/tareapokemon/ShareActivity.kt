package com.example.tareapokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tareapokemon.models.Pokemon

class ShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        var pokemonInfo: Pokemon = intent?.extras?.getParcelable("Pokemon")?: Pokemon()

        initActivity(pokemonInfo)
    }

    fun initActivity(pokemon: Pokemon){

    }


}
