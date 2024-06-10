package com.example.balisnap.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.balisnap.DetailActivity
import com.example.balisnap.R
import com.example.balisnap.databinding.ItemWisataBinding
import com.example.balisnap.response.DestinationsItem

class MainAdapter(private var listwisata: List<DestinationsItem?>, private val listener: OnItemClickListener) : RecyclerView.Adapter<MainAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_wisata, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listwisata.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val story = listwisata[position]
        if (story != null) {
            holder.bind(story)
            holder.itemView.setOnClickListener {
                listener.onItemClick(story)
            }
        }
    }

    inner class ListViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding.rootView) {
        private val nama: TextView = binding.findViewById(R.id.tv_item_name)
        private val foto: ImageView = binding.findViewById(R.id.tv_detail_photo)
        private val deskripsi: TextView = binding.findViewById(R.id.tv_item_desc)

        fun bind(story: DestinationsItem) {
            nama.text = story.name
            deskripsi.text = story.description
            Log.d("StoryAdapter", "Binding story: $story.name")
            Glide.with(binding.context)
                .load(story.image)
                .into(foto)
        }
    }

//    fun updateStories(newStories: List<DestinationsItem?>) {
//        listStory = newStories
//        Log.d("StoryAdapter", "Updated stories: $newStories")
//        notifyDataSetChanged()
//    }

    interface OnItemClickListener{
        fun onItemClick(wisata: DestinationsItem)
    }

    companion object {

        const val IMAGE_STORY = "IMAGE_STORY"
        const val TITLE_STORY = "TITLE_STORY"
        const val DESC_STORY = "DESC_STORY"

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DestinationsItem>() {
            override fun areItemsTheSame(oldItem: DestinationsItem, newItem: DestinationsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DestinationsItem,
                newItem: DestinationsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}