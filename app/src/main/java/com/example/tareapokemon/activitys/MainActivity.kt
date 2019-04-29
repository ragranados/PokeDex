package com.example.tareapokemon.activitys

import android.content.Intent
import android.content.res.Configuration
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.tareapokemon.models.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.net.URL
import android.util.Log
import android.widget.TextView
import com.example.tareapokemon.adapters.PokemonAdapter
import com.example.tareapokemon.AsyncResponse
import com.example.tareapokemon.R
import com.example.tareapokemon.fragments.LandPokemonContent
import com.example.tareapokemon.utils.NetworkUtility
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    lateinit var pokemon: MutableList<Pokemon>
    var viewAdapter: RecyclerView.Adapter<*>? = null
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState!=null){
            pokemon = savedInstanceState.getParcelableArrayList("list")
            setUpView(pokemon)
        }else{
            init()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {

        var list: ArrayList<Pokemon> = ArrayList()
        list.addAll(pokemon.toList())
        outState.putParcelableArrayList("list",list)
        super.onSaveInstanceState(outState)

    }

    fun init() {

        FetchPokemonTask(object : AsyncResponse {
            override fun proccesFinish(outPut: String?){
                Log.d("pruebaejec","se ejecuta XD")
                makeList(getPokemonMutableList(outPut))
            }
        }).execute()

    }

    fun getPokemonMutableList(outPut: String?): MutableList<Pokemon>{
        var pokemons: JSONArray = JSONObject(outPut).getJSONArray("results")
        var pokemon: MutableList<Pokemon> = MutableList(100) { i ->
            Pokemon(i.toString(), JSONObject(pokemons.getString(i)).getString("name"), JSONObject(pokemons.getString(i)).getString("url"))
        }

        return pokemon
    }

    fun makeList(outPut: MutableList<Pokemon>) {

        pokemon = MutableList(100) { i ->
            Pokemon(i.toString(), outPut[i].name, outPut[i].url)
        }
        setUpView(pokemon)

    }

    fun setUpView(pokemon: MutableList<Pokemon>){

        viewManager = LinearLayoutManager(this)
        //viewAdapter = PokemonAdapter(pokemon, { pokemonItem: Pokemon -> itemClickedPortrait(pokemonItem) })

        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            viewAdapter = PokemonAdapter(pokemon, { pokemonItem: Pokemon -> itemClickedPortrait(pokemonItem) })
        }else{
            var contentFragmet : LandPokemonContent =LandPokemonContent.newInstance(Pokemon())
            viewAdapter = PokemonAdapter(pokemon, { pokemonItem: Pokemon -> itemClickedLandscape(pokemonItem) })
            changeFragment(R.id.land_pokemon_detail, contentFragmet)
        }


        pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun itemClickedPortrait(pokemon: Pokemon){
        Log.d("prueba:v","https://pokeapi.co/api/v2/pokemon/${pokemon.name}/")

        var pokemonBundle = Bundle()
        pokemonBundle.putParcelable("Pokemon",pokemon)

        startActivity(Intent(this, ShareActivity::class.java).putExtras(pokemonBundle))

    }

    fun itemClickedLandscape(pokemon: Pokemon){
        var contentFragmet : LandPokemonContent =LandPokemonContent.newInstance(pokemon)
        changeFragment(R.id.land_pokemon_detail, contentFragmet)

    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    class FetchPokemonTask(var asyncResponseOut: AsyncResponse? = null) : AsyncTask<String, Void, String>() {

        var asyncResponse: AsyncResponse? = null

        init {
            this.asyncResponse = asyncResponseOut
        }

        override fun doInBackground(vararg params: String?): String? {

            val BASE_URL = "https://pokeapi.co/api/v2/"
            val URL_ALL  = "pokemon/?offset=0&limit=100"

            val pokeAPI: URL = NetworkUtility().buildUrl(BASE_URL+URL_ALL)
            try {
                return NetworkUtility().getResponseFromHttpUrl(pokeAPI)
            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }
        }

        override fun onPostExecute(pokemonInfo: String) {
            /*var pokemons: JSONArray = JSONObject(pokemonInfo).getJSONArray("results")
            var pokemon: MutableList<Pokemon> = MutableList(100) { i ->
                Pokemon(i.toString(), JSONObject(pokemons.getString(i)).getString("name"), JSONObject(pokemons.getString(i)).getString("url"))
            }*/

            asyncResponse!!.proccesFinish(pokemonInfo)

        }
    }
}
