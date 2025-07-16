package com.example.finalexamgorgadze
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class Adapter(
    private val games: MutableList<Games>,
    private val onSaveClicked: (Games) -> Unit
) : RecyclerView.Adapter<Adapter.GameViewHolder>() {

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameImage: ImageView = itemView.findViewById(R.id.poster)
        val gameTitle: TextView = itemView.findViewById(R.id.title)
        val gameGenre: TextView = itemView.findViewById(R.id.details)
        val saveIcon: ImageView = itemView.findViewById(R.id.save)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.gameTitle.text = game.Title
        holder.gameGenre.text = game.Genre

        Glide.with(holder.itemView.context)
            .load(game.Poster)
            .transform(CenterCrop(), RoundedCorners(24))
            .placeholder(R.drawable.posterimg_background)
            .into(holder.gameImage)

        val context = holder.itemView.context
        val isSaved = SavedGames.set.contains(game)

        holder.saveIcon.setImageResource(
            if (isSaved) R.drawable.savep else R.drawable.save
        )
        holder.saveIcon.isClickable = !isSaved
        holder.saveIcon.setOnClickListener {
            if (!SavedGames.set.contains(game)) {
                SavedGames.set.add(game)
                holder.saveIcon.setImageResource(R.drawable.savep)
                Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
                onSaveClicked(game)

                holder.saveIcon.isClickable = false
            }
        }
    }

    override fun getItemCount(): Int = games.size
}




