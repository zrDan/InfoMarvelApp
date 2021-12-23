package com.gtdan.marvelucm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gtdan.marvelucm.databinding.PosterItemBinding
import com.gtdan.marvelucm.network.model.ItemModel

class ItemsListAdapter(private val onItemClicked: (ItemModel) -> Unit) :
    ListAdapter<ItemModel, ItemsListAdapter.ItemModelViewHolder>(DiffCallback) {

    class ItemModelViewHolder(private var binding: PosterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemModel: ItemModel) {
            binding.item = itemModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsListAdapter.ItemModelViewHolder {

        val viewHolder = ItemModelViewHolder(
            PosterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemsListAdapter.ItemModelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ItemModel>() {
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }
    }
}