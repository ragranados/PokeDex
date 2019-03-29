package com.example.tareapokemon

import com.example.tareapokemon.models.Pokemon

public interface AsyncResponse{
    fun proccesFinish(outPut: MutableList<Pokemon>)
}