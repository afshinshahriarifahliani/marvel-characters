package com.afshinshahriarifahliani.marvel_characters.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.ItemDetailsResult
import com.afshinshahriarifahliani.marvel_characters.databinding.ItemDetailsCardviewBinding
import com.afshinshahriarifahliani.marvel_characters.util.getCircularProgress
import com.afshinshahriarifahliani.marvel_characters.util.loadImage

class ItemDetailsAdapter :
    ListAdapter<ItemDetailsResult, ItemDetailsAdapter.ItemDetailsViewHolder>(ItemDiffCallBack()) {
    class ItemDiffCallBack : DiffUtil.ItemCallback<ItemDetailsResult>() {
        override fun areItemsTheSame(
            oldItem: ItemDetailsResult,
            newItem: ItemDetailsResult
        ): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: ItemDetailsResult,
            newItem: ItemDetailsResult
        ): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDetailsViewHolder {
        val binding = ItemDetailsCardviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemDetailsViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<ItemDetailsResult>) {
        submitList(data.toMutableList())
    }

    inner class ItemDetailsViewHolder(
        private val binding: ItemDetailsCardviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: ItemDetailsResult) {
            binding.apply {
                if(item.thumbnail!=null){
                    itemImage.loadImage(
                        item.thumbnail.path + "/portrait_medium." + item.thumbnail.extension,
                        getCircularProgress(itemImage.context)
                    )

                }

                itemName.text = item.title

                if (item.description != "") {
                    itemDescription.text = item.description
                }
            }
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }

    }

    private var onItemClickListener: ((ItemDetailsResult) -> Unit)? = null

    fun setOnItemClickListener(listener: (ItemDetailsResult) -> Unit) {
        onItemClickListener = listener
    }


}