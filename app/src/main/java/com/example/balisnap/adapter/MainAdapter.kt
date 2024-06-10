package com.example.balisnap.adapter

import android.annotation.SuppressLint
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

class MainAdapter : ListAdapter<DestinationsItem, MainAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story)
        Glide.with(holder.imgstory.getContext())
            .load(story.image)
            .into(holder.imgstory);
//        holder.itemView.setOnClickListener {
//            val intentDetailStory =
//                Intent(holder.itemView.context, DetailStoryActivity::class.java)
//            holder.itemView.context.startActivity(intentDetailStory)
//        }
    }

    class MyViewHolder(val binding: ItemWisataBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgstory: ImageView = itemView.findViewById(R.id.img_item_photo)
        fun bind(story: DestinationsItem) {
            binding.tvItemName.text = "${story.name}"
            binding.tvItemDesc.text = "${story.description}"
        }
    }

    companion object {


        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DestinationsItem>() {
            override fun areItemsTheSame(oldItem: DestinationsItem, newItem: DestinationsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DestinationsItem, newItem: DestinationsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}