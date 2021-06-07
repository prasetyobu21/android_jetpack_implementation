package com.pras.submission1jetpack.model.remote

import android.content.Context
import android.util.Log
import com.pras.submission1jetpack.model.remote.response.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadTvList(): List<TvListResponse> {
        val list = ArrayList<TvListResponse>()
        Log.d("tvlistdata", "JSONHelper Is running!")
        try {
            val responseObject = JSONObject(parsingFileToString("tvList.json").toString())
            val listArray = responseObject.getJSONArray("results")
            Log.d("tvlistdata", "JSONHelper Is tried!")
            Log.d("tvlistdata", responseObject.toString())
            for (i in 0 until listArray.length()) {
                val tvList = listArray.getJSONObject(i)

                val id = tvList.getString("id")
                val name = tvList.getString("name")
                val vote_average = tvList.getString("vote_average")
                val poster_path = tvList.getString("poster_path")
                val first_air_date = tvList.getString("first_air_date")
                val overview = tvList.getString("overview")

                val tvListResponse = TvListResponse(id.toInt(), name, vote_average.toDouble(), poster_path, first_air_date, overview)
                list.add(tvListResponse)
            }
            Log.d("tvdata", list.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTvDetail(id: Int): TvListResponse {
        var tvDetail: TvListResponse? = null
        try {
            val list = loadTvList()

            for (data in list){
                if (data.id == id){
                    tvDetail = data
                    break
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return tvDetail as TvListResponse
    }

    fun loadMovieList(): List<MovieListResponse> {
        val list = ArrayList<MovieListResponse>()
        Log.d("movieListData", "JSONHelper Is running!")
        try {
            val responseObject = JSONObject(parsingFileToString("movieList.json").toString())
            val listArray = responseObject.getJSONArray("results")
            Log.d("movieListData", "JSONHelper Is tried!")
            Log.d("movieListData", responseObject.toString())
            for (i in 0 until listArray.length()) {
                val movieList = listArray.getJSONObject(i)

                val id = movieList.getString("id")
                val original_title = movieList.getString("original_title")
                val vote_average = movieList.getString("vote_average")
                val poster_path = movieList.getString("poster_path")
                val release_date = movieList.getString("release_date")
                val overview = movieList.getString("overview")

                val movieListResponse = MovieListResponse(id.toInt(), original_title, vote_average.toDouble(), poster_path, release_date, overview)
                list.add(movieListResponse)
            }
            Log.d("movieListData", list.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadMovieDetail(id: Int): MovieListResponse {
        var movieDetail: MovieListResponse? = null
        try {
            val list = loadMovieList()

            for (data in list){
                if(data.id == id){
                    movieDetail = data
                    break
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return movieDetail as MovieListResponse
    }
}