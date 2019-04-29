package com.example.tareapokemon.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.bumptech.glide.Glide
import com.example.tareapokemon.R
import com.example.tareapokemon.fragments.LandPokemonContent
import com.example.tareapokemon.models.Pokemon
import kotlinx.android.synthetic.main.activity_share.*

class ShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        var pokemonInfo: Pokemon = intent?.extras?.getParcelable("Pokemon")?: Pokemon()

        initActivity(pokemonInfo)
    }

    fun initActivity(pokemon: Pokemon){
        var contentFragmet : LandPokemonContent = LandPokemonContent.newInstance(pokemon)
        changeFragment(R.id.content, contentFragmet)
        Log.d("fin","fin")

    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

}
