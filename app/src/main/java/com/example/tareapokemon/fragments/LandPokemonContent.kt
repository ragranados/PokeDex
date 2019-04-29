package com.example.tareapokemon.fragments

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tareapokemon.AsyncResponse

import com.example.tareapokemon.R
import com.example.tareapokemon.models.Pokemon
import com.example.tareapokemon.utils.NetworkUtility
import kotlinx.android.synthetic.main.fragment_land_pokemon_content.*
import kotlinx.android.synthetic.main.fragment_land_pokemon_content.view.*
import kotlinx.android.synthetic.main.pokemon_list_item.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.URL


class LandPokemonContent : Fragment() {
    // TODO: Rename and change types of parameters
    private var pokemon = Pokemon()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_land_pokemon_content, container, false)

        bindData(view)

        initFragment()
        return view
    }

    fun initFragment(){

    }

    fun bindData(view: View){

        Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id.toInt()+1}.png")
                .into(view.land_image)
        view.land_pokemon_name.text = pokemon.name
        view.land_pokemon_weight.text = pokemon.url
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(pokemon: Pokemon):LandPokemonContent{
            val newFragment= LandPokemonContent()
            newFragment.pokemon = pokemon
            return newFragment

        }
    }



    /*class FetchPokemonTask(var asyncResponseOut: AsyncResponse? = null) : AsyncTask<String, Void, String>() {

        var asyncResponse: AsyncResponse? = null

        init {
            this.asyncResponse = asyncResponseOut
        }

        override fun doInBackground(vararg params: String?): String? {
            val pokeAPI: URL = NetworkUtility().buildUrl(params[0])
            try {
                return NetworkUtility().getResponseFromHttpUrl(pokeAPI)
            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }
        }

        override fun onPostExecute(pokemonInfo: String) {
            var pokemons: JSONArray = JSONObject(pokemonInfo).getJSONArray("results")
            var pokemon: MutableList<Pokemon> = MutableList(100) { i ->
                Pokemon(i.toString(), JSONObject(pokemons.getString(i)).getString("name"), JSONObject(pokemons.getString(i)).getString("url"))
            }

            asyncResponse!!.proccesFinish(pokemon)

        }
    }*/
}
