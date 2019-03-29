package com.example.tareapokemon

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.tareapokemon.models.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.net.URL
import android.provider.Settings.System.getString
import android.view.View
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {


    private lateinit var pruebaView: TextView
    var viewAdapter: RecyclerView.Adapter<*>? = null
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pruebaView = prueba
        init()
    }

    fun init() {
        var pokemons = FetchPokemonTask(object : AsyncResponse {
            override fun proccesFinish(outPut: MutableList<Pokemon>) {
                makeList(outPut)
            }
        }).execute()
    }

    fun makeList(outPut: MutableList<Pokemon>) {

        lateinit var pokemon: MutableList<Pokemon>

        pokemon = MutableList(100) { i ->
            Pokemon(i, outPut[i].name, outPut[i].url)
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = PokemonAdapter(pokemon)


        pokemon_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    class FetchPokemonTask(var asyncResponseOut: AsyncResponse? = null) : AsyncTask<String, Void, String>() {

        var asyncResponse: AsyncResponse? = null

        init {
            this.asyncResponse = asyncResponseOut
        }

        override fun doInBackground(vararg params: String?): String? {

            val pokeAPI: URL = NetworkUtility().buildUrl()

            try {
                return NetworkUtility().getResponseFromHttpUrl(pokeAPI)
            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }
        }

        override fun onPostExecute(pokemonInfo: String) {


            var pokemons: JSONArray = JSONObject(pokemonInfo).getJSONArray("results")
            //var prueba : String = JSONObject(pokemonInfo).toString()

            var pokemon: MutableList<Pokemon> = MutableList(100) { i ->
                Pokemon(i, JSONObject(pokemons.getString(i)).getString("name"), JSONObject(pokemons.getString(i)).getString("url"))
            }

            asyncResponse!!.proccesFinish(pokemon)

        }


    }

    class DownloadImagen
}