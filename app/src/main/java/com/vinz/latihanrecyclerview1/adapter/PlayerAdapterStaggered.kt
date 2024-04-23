package com.vinz.latihanrecyclerview1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.vinz.latihanrecyclerview1.R
import com.vinz.latihanrecyclerview1.data.PlayerData

class PlayerAdapterStaggered(private val playerList: List<PlayerData>) : RecyclerView.Adapter<PlayerAdapterStaggered.PlayerViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: PlayerData)
    }

    class PlayerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: MaterialTextView = itemView.findViewById(R.id.player_name)
        val playerDescription: MaterialTextView = itemView.findViewById(R.id.player_description)
        val playerImage: ShapeableImageView = itemView.findViewById(R.id.player_image)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_player_grid, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val data = playerList[position]

        holder.playerName.text = data.name
        holder.playerDescription.text = data.description.shorten(85)
        holder.playerImage.setImageResource(data.image)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(playerList[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = playerList.size

    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}