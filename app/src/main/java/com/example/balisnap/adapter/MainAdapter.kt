package com.example.balisnap.adapter

import android.content.Intent
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
        val users = getItem(position)
        holder.bind(users)


    }

    class MyViewHolder(val binding: ItemWisataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(wisata: DestinationsItem) {

            binding.itemWisata.setOnClickListener{
                val intent = Intent(binding.itemWisata.context, DetailActivity::class.java)
                binding.itemWisata.context.startActivity(intent)
            }
            binding.tvItemName.text = wisata.name
            Glide.with(binding.root.context)
                .load(wisata.image)
                .circleCrop()
                .into(binding.imgItemPhoto)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val namaWisata : TextView = view.findViewById(R.id.tv_item_name)
        val foto : ImageView = view.findViewById(R.id.img_item_photo)
        val desc :TextView = view.findViewById(R.id.tv_item_desc)
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
