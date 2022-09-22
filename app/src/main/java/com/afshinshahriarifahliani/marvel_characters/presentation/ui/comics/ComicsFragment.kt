package com.afshinshahriarifahliani.marvel_characters.presentation.ui.comics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.afshinshahriarifahliani.marvel_characters.MainActivity
import com.afshinshahriarifahliani.marvel_characters.databinding.FragmentComicsBinding
import com.afshinshahriarifahliani.marvel_characters.presentation.adapter.ItemDetailsAdapter
import com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel.MarvelViewModel
import com.afshinshahriarifahliani.marvel_characters.util.Resource

class ComicsFragment(private val characterId: Int) : Fragment() {

    private var _binding: FragmentComicsBinding? = null
    private lateinit var marvelViewModel: MarvelViewModel
    private lateinit var comicsAdapter: ItemDetailsAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentComicsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        marvelViewModel = (activity as MainActivity).marvelViewModel
        comicsAdapter = (activity as MainActivity).itemDetailsAdapter


        initRecyclerView()
        viewComics()
        return root
    }

    private fun viewComics() {
        marvelViewModel.getCharacterComics(characterId)
        marvelViewModel.comics.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        comicsAdapter.swapData(it.data.results.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }

                else -> {}
            }
        }
    }

    private fun initRecyclerView() {
        binding.comicsRecyclerView.apply {
            adapter = comicsAdapter
            layoutManager = GridLayoutManager(activity, 2)
            setHasFixedSize(true)
        }

    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}