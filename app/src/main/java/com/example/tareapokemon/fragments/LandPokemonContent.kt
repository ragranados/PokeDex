package com.example.tareapokemon.fragments

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tareapokemon.AsyncResponse

import com.example.tareapokemon.R
import com.example.tareapokemon.activitys.MainActivity
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
    lateinit var fragmentView: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_land_pokemon_content, container, false)

        fragmentView = view

        initFragment()
        return view
    }

    fun initFragment(){
        FetchPokemonInfoTask(object : AsyncResponse {
            override fun proccesFinish(outPut: String?) {
                bindData(outPut)
            }
        }).execute(pokemon.id)
    }

    fun bindData(outPut: String?){

        if(isAdded){
            Glide.with(this)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id.toInt()+1}.png")
                    .into(fragmentView.land_image)
            fragmentView.land_pokemon_name.text = JSONObject(outPut).getJSONArray("forms").getJSONObject(0).getString("name")
            fragmentView.land_pokemon_weight.text = "Weight: "+ "\t"+JSONObject(outPut).getString("weight")
        }
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





    class FetchPokemonInfoTask(var asyncResponseOut: AsyncResponse? = null) : AsyncTask<String, Void, String>() {

        var asyncResponse: AsyncResponse? = null

        init {
            this.asyncResponse = asyncResponseOut
        }

        override fun doInBackground(vararg params: String?): String? {

            val id :String? = params[0]
            val pokeAPI: URL = NetworkUtility().buildUrl("https://pokeapi.co/api/v2/pokemon/${id?.toInt()?.plus(1)}/")
            try {
                return NetworkUtility().getResponseFromHttpUrl(pokeAPI)
            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }
        }

        override fun onPostExecute(pokemonInfo: String) {

            asyncResponse!!.proccesFinish(pokemonInfo)

        }
    }
}
