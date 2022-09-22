package com.afshinshahriarifahliani.marvel_characters.presentation.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.afshinshahriarifahliani.marvel_characters.MainActivity
import com.afshinshahriarifahliani.marvel_characters.R
import com.afshinshahriarifahliani.marvel_characters.databinding.FragmentCharactersBinding
import com.afshinshahriarifahliani.marvel_characters.presentation.adapter.CharacterAdapter
import com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel.MarvelViewModel
import com.afshinshahriarifahliani.marvel_characters.util.LIMIT

class FavoritesFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private lateinit var marvelViewModel: MarvelViewModel
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        marvelViewModel = (activity as MainActivity).marvelViewModel
        characterAdapter = (activity as MainActivity).characterAdapter
        characterAdapter.setOnItemClickListener {
            val action = FavoritesFragmentDirections.actionNavigationFavoritesToNavigationCharacterDetails(it)
            findNavController().navigate(action)
        }
        initRecyclerView()
        viewSavedCharactersList()

        binding.characterSearchView.visibility = View.GONE
        val swipe = binding.swipeToRefresh
        swipe.setOnRefreshListener {
            viewSavedCharactersList()
            swipe.isRefreshing = false
        }


    }

    private fun viewSavedCharactersList() {

        marvelViewModel.getAllSavedCharacters()
        marvelViewModel.getAllSavedCharacters().observe(viewLifecycleOwner) { characters ->

            characterAdapter.swapData( characters.sortedBy {
                it.name
            })
        }
    }

    private fun initRecyclerView() {
        binding.characterRecyclerView.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}