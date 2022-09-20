package com.afshinshahriarifahliani.marvel_characters.presentation.ui.characterInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.afshinshahriarifahliani.marvel_characters.BuildConfig
import com.afshinshahriarifahliani.marvel_characters.MainActivity
import com.afshinshahriarifahliani.marvel_characters.databinding.FragmentCharacterInfoBinding
import com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel.MarvelViewModel
import com.afshinshahriarifahliani.marvel_characters.util.digest
import com.google.android.material.snackbar.Snackbar
import org.chromium.base.Log
import java.sql.Timestamp

class CharacterInfoFragment : Fragment() {

    private lateinit var characterInfoViewModel : MarvelViewModel


    private var _binding: FragmentCharacterInfoBinding? = null

    private val binding get() = _binding!!

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

        val args :CharacterInfoFragmentArgs  by navArgs()
        val character = args.selectedCharacter

        characterInfoViewModel = (activity as MainActivity).marvelViewModel

        val ts = Timestamp(System.currentTimeMillis()).toString()
        val hash=digest(ts,BuildConfig.API_KEY,BuildConfig.PRI_KEY)
//        binding.characterWebView.apply {
//            webViewClient = WebViewClient()
//            loadUrl(character.resourceURI+"?ts=$ts&apikey=${BuildConfig.API_KEY}&hash=$hash")
//Log.i("MYURL",character.resourceURI+"?ts=$ts&apikey=${BuildConfig.API_KEY}&hash=$hash")
//        }
//        binding.fabFavorite.setOnClickListener {
//            characterInfoViewModel.saveCharacter(character)
//            Snackbar.make(view,"Saved Successfully!", Snackbar.LENGTH_LONG).show()
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}