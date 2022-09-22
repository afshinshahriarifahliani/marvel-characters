package com.afshinshahriarifahliani.marvel_characters.presentation.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afshinshahriarifahliani.marvel_characters.MainActivity
import com.afshinshahriarifahliani.marvel_characters.R
import com.afshinshahriarifahliani.marvel_characters.databinding.FragmentCharactersBinding
import com.afshinshahriarifahliani.marvel_characters.presentation.adapter.CharacterAdapter
import com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel.MarvelViewModel
import com.afshinshahriarifahliani.marvel_characters.util.LIMIT
import com.afshinshahriarifahliani.marvel_characters.util.OFFSET
import com.afshinshahriarifahliani.marvel_characters.util.Resource
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private lateinit var marvelViewModel: MarvelViewModel
    private lateinit var characterAdapter: CharacterAdapter
    private var offset = OFFSET
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0


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
            val bundle = Bundle().apply {
                putSerializable("selected_character", it)
            }
            findNavController().navigate(
                R.id.action_navigation_characters_to_navigation_character_details,
                bundle
            )
        }
        initRecyclerView()
        viewCharactersList()
        setSearchView()
        val swipe = binding.swipeToRefresh
        swipe.setOnRefreshListener {
            offset += LIMIT
            viewCharactersList()
            swipe.isRefreshing = false
        }


    }

    private fun viewCharactersList() {

        marvelViewModel.getAllCharacters(offset)
        marvelViewModel.characters.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        characterAdapter.swapData(it.data.characters.toList())
                        pages = if (it.data.total % LIMIT == 0) {
                            it.data.total / LIMIT
                        } else {
                            it.data.total / LIMIT + 1
                        }
                        isLastPage = (offset >= pages * LIMIT)
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
        binding.characterRecyclerView.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@CharactersFragment.onScrollListener)
        }

    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }

        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.characterRecyclerView.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if (shouldPaginate) {
                offset += LIMIT
                marvelViewModel.getAllCharacters(offset)
                isScrolling = false

            }


        }
    }

    //search
    private fun setSearchView(){
        binding.characterSearchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    marvelViewModel.searchCharacterNameToStartWithUseCase(query.toString(), OFFSET)
                    viewCharacterSearchedResult()
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    MainScope().launch {
                        delay(2000)
                        marvelViewModel.searchCharacterNameToStartWithUseCase(query.toString(), OFFSET)
                        viewCharacterSearchedResult()
                    }
                    return false
                }

            })

        binding.characterSearchView.setOnCloseListener {
            initRecyclerView()
            viewCharacterSearchedResult()
            false
        }
    }




    fun viewCharacterSearchedResult(){
        marvelViewModel.characterSearchResult.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        characterAdapter.swapData(it.data.characters.toList())
                        pages = if (it.data.total % LIMIT == 0) {
                            it.data.total / LIMIT
                        } else {
                            it.data.total / LIMIT + 1
                        }
                        isLastPage = (offset >= pages * LIMIT)
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

}