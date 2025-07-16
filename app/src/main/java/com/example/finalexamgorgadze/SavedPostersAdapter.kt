package com.example.finalexamgorgadze

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class SavedPostersAdapter(
    private val savedGames: MutableList<Games>
) : RecyclerView.Adapter<SavedPostersAdapter.PosterViewHolder>() {

    class PosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImage: ImageView = itemView.findViewById(R.id.savedPoster)
        val gameTitle: TextView = itemView.findViewById(R.id.savedTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.second_item, parent, false)
        return PosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        val game = savedGames[position]
        holder.gameTitle.text = game.Title

        Glide.with(holder.itemView.context)
            .load(game.Poster)
            .transform(CenterCrop(), RoundedCorners(24))
            .placeholder(R.drawable.posterimg_background)
            .into(holder.posterImage)


    }
    override fun getItemCount(): Int = savedGames.size
}

