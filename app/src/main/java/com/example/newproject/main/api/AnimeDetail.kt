package com.example.newproject.main.api

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.example.newproject.databinding.AnimeDetailLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class AnimeDetail(
    private val anime: Result
) : BottomSheetDialogFragment() {

    lateinit var binding: AnimeDetailLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val animeInflater = LayoutInflater.from(requireContext())
        binding = AnimeDetailLayoutBinding.inflate(animeInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            Picasso.get().load(anime.images.jpg.image_url).into(image)
            name.text = anime.title
            rating.text = anime.score.toString()
            pgRating.text = anime.rated
            episodes.text = "${anime.episodes} episodes"
            synopsis.text = anime.synopsis

            knowMoreText.setOnClickListener {
                openCustomTab(activity as AppCompatActivity, Uri.parse(anime.url))
            }
        }
    }

    private fun openCustomTab(activity: AppCompatActivity, url: Uri) {
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.build().launchUrl(activity, url)
    }

    private fun formatDate(date: String): String {
        return if (date.contains("-")) {
            val newDate = date.substring(0, date.lastIndexOf("-"))
            val _date = SimpleDateFormat("yyyy-MM").parse(newDate)
            SimpleDateFormat("MMM yyyy").format(_date)
        } else {
            date
        }
    }

}

