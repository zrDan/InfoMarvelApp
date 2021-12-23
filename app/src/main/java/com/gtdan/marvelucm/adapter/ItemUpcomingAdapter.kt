package com.gtdan.marvelucm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gtdan.marvelucm.databinding.PosterUpcomingItemBinding
import com.gtdan.marvelucm.network.model.ItemUpcomingModel


class ItemUpcomingAdapter: ListAdapter<ItemUpcomingModel, ItemUpcomingAdapter.ItemUpcomingModelViewHolder>(DiffCallback){

    class ItemUpcomingModelViewHolder(private var binding: PosterUpcomingItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (itemUpcomingModel: ItemUpcomingModel){
            binding.item = itemUpcomingModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemUpcomingModelViewHolder {
        return ItemUpcomingModelViewHolder(PosterUpcomingItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemUpcomingModelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback: DiffUtil.ItemCallback<ItemUpcomingModel>(){
        override fun areItemsTheSame(oldItem: ItemUpcomingModel, newItem: ItemUpcomingModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemUpcomingModel, newItem: ItemUpcomingModel): Boolean {
            return  oldItem == newItem
        }
    }

}