package com.example.tareapokemon.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.tareapokemon.R
import com.example.tareapokemon.models.Pokemon
import kotlinx.android.synthetic.main.fragment_land_pokemon_content.*
import kotlinx.android.synthetic.main.fragment_land_pokemon_content.view.*
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LandPokemonContent.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LandPokemonContent.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LandPokemonContent : Fragment() {
    // TODO: Rename and change types of parameters
    private var pokemon = Pokemon()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_land_pokemon_content, container, false)

        bindData(view)

        return view
    }

    fun bindData(view: View){

        Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id.toInt()+1}.png")
                .into(view.land_image)
        view.land_pokemon_name.text = pokemon.name
        view.land_pokemon_weight.text = pokemon.url
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LandPokemonContent.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(pokemon: Pokemon):LandPokemonContent{
            val newFragment= LandPokemonContent()
            newFragment.pokemon = pokemon
            return newFragment

        }
    }
}
