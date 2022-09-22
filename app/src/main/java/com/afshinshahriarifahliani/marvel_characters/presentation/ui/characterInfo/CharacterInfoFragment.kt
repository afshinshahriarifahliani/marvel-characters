package com.afshinshahriarifahliani.marvel_characters.presentation.ui.characterInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.afshinshahriarifahliani.marvel_characters.MainActivity
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelCharacter
import com.afshinshahriarifahliani.marvel_characters.databinding.FragmentCharacterInfoBinding
import com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel.MarvelViewModel
import com.afshinshahriarifahliani.marvel_characters.util.Resource
import com.google.android.material.snackbar.Snackbar

class CharacterInfoFragment(private val characterId: Int) : Fragment() {

    private lateinit var characterInfoViewModel: MarvelViewModel
    private var _binding: FragmentCharacterInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var character: MarvelCharacter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterInfoViewModel = (activity as MainActivity).marvelViewModel

        characterInfoViewModel.getSingleCharacterById(characterId)
        characterInfoViewModel.singleCharacter.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {

                    response.data?.let {
                        character = it.data.characters[0]
                        binding.characterWebView.apply {
                            webViewClient = WebViewClient()
                            loadUrl(character.urls[0].url)
                        }
                    }
                }

                is Resource.Error -> {
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                }

                else -> {}
            }

        }


        binding.fabFavorite.setOnClickListener {
            characterInfoViewModel.addToFavorites(character)
            Snackbar.make(view, "Character added to favorites successfully", Snackbar.LENGTH_LONG)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}