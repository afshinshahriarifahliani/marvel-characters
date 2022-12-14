package com.afshinshahriarifahliani.marvel_characters.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelCharacter
import com.afshinshahriarifahliani.marvel_characters.databinding.CharacterCardviewBinding
import com.afshinshahriarifahliani.marvel_characters.util.getCircularProgress
import com.afshinshahriarifahliani.marvel_characters.util.loadImage

class CharacterAdapter :
    ListAdapter<MarvelCharacter, CharacterAdapter.CharacterViewHolder>(CharacterDiffCallBack()) {
    class CharacterDiffCallBack : DiffUtil.ItemCallback<MarvelCharacter>() {
        override fun areItemsTheSame(
            oldItem: MarvelCharacter,
            newItem: MarvelCharacter
        ): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: MarvelCharacter,
            newItem: MarvelCharacter
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterCardviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<MarvelCharacter>) {
        submitList(data.toMutableList())
    }

    inner class CharacterViewHolder(
        private val binding: CharacterCardviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(character: MarvelCharacter) {
            binding.apply {

                characterThumbnail.loadImage(
                    character.thumbnail.path + "/portrait_large." + character.thumbnail.extension,
                    getCircularProgress(characterThumbnail.context)
                )

                characterName.text = character.name

                if (character.description != "") {
                    characterDescription.text = character.description
                }

                characterComicsValue.text = "Comics ${character.comics.available}"
                characterSeriesValue.text = "Series ${character.series.available}"
                characterStoriesValue.text = "Stories ${character.comics.available}"
                characterEventsValue.text = "Events ${character.comics.available}"

            }
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(character)
                }
            }
        }

    }

    private var onItemClickListener: ((MarvelCharacter) -> Unit)? = null

    fun setOnItemClickListener(listener: (MarvelCharacter) -> Unit) {
        onItemClickListener = listener
    }


}