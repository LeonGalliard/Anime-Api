package com.example.newproject.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newproject.databinding.ActivityMainBinding
import com.example.newproject.main.api.AnimeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listAnimeLokal = mutableListOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        setupSearchButton()
    }

    private fun getData() {
        val animeService = AnimeService.create()
        val call = animeService.getTopAnimes()

        call.enqueue(object : Callback<TopAnime> {

            override fun onResponse(call: Call<TopAnime>, response: Response<TopAnime>) {
                if (response.body() != null) {
                    val top = response.body()!!.top
                    listAnimeLokal.addAll(top)
                    binding.animeRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 3)
                    binding.animeRecyclerView.adapter = AnimeAdapter(listAnimeLokal, this@MainActivity)
                }
            }

            override fun onFailure(call: Call<TopAnime>, t: Throwable) {
                binding.animeRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 3)
                binding.animeRecyclerView.adapter = AnimeAdapter(listAnimeLokal, this@MainActivity)
            }
        })
    }

    private fun setupSearchButton() {
        binding.btnSearch.setOnClickListener {
            val searchedAnime = binding.searchInputEditText.text.toString()
            val animeService = AnimeService.create()
            val callSearchedAnime = animeService.getSearchedAnime(searchedAnime)

            callSearchedAnime.enqueue(object : Callback<SearchedAnime> {

                override fun onResponse(
                    call: Call<SearchedAnime>,
                    response: Response<SearchedAnime>
                ) {
                    if (response.body() != null) {
                        val searchedAnimes = response.body()!!.results
                        binding.animeRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 3)
                        binding.animeRecyclerView.adapter = AnimeAdapter(searchedAnimes, this@MainActivity)
                    }
                }

                override fun onFailure(call: Call<SearchedAnime>, t: Throwable) {
                }
            })
        }
    }
}
