package com.example.tareapokemon.Utils

import android.net.Uri
import android.view.View
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*


class NetworkUtility(){

    val BASE_URL: String = "https://pokeapi.co/api/v2/"

    val URL_ALL: String = "pokemon/?offset=0&limit=100"

    fun buildUrl(): URL {
        val builtUri = Uri.parse(BASE_URL+URL_ALL)
            /*.buildUpon()
            .appendPath(URL)
            .build()*/

        lateinit var url: URL
        try {
            url = URL(builtUri.toString())
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        return url
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL): String? {
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val `in` = urlConnection.getInputStream()
            //val `fot` = urlConnection.getInputStream()

            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
             if (hasInput) {
                return scanner.next()
            } else {
                return null
            }
        } finally {
            urlConnection.disconnect()
        }
    }



}