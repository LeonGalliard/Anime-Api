package com.example.newproject.main

import android.app.ProgressDialog.show
import com.example.newproject.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class AnimeAdapter (val listAnime: List<Result>,val konteks: AppCompatActivity):RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>(){

    class AnimeViewHolder(baris: View):RecyclerView.ViewHolder(baris){
        val name = baris.findViewById<TextView>(R.id.name)
        val image = baris.findViewById<ImageView>(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.anime_item_layout,parent,false)
        return AnimeViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return listAnime.size
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val bind = listAnime[position]

        holder.name.text = bind.title

        Picasso.get().load(bind.images.jpg.image_url).into(holder.image)

        holder.image.setOnClickListener {
            AnimeDetail(listAnime[position]).apply {
                show(konteks.supportFragmentManager, "AnimeDetailBottomSheet")
            }
        }

    }
}