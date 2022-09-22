package com.afshinshahriarifahliani.marvel_characters.presentation.ui.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.afshinshahriarifahliani.marvel_characters.R
import com.afshinshahriarifahliani.marvel_characters.databinding.FragmentCharacterDetailsBinding
import com.afshinshahriarifahliani.marvel_characters.presentation.adapter.SingleCharacterViewPagerAdapter
import com.afshinshahriarifahliani.marvel_characters.presentation.ui.characterInfo.CharacterInfoFragment
import com.afshinshahriarifahliani.marvel_characters.presentation.ui.comics.ComicsFragment
import com.afshinshahriarifahliani.marvel_characters.presentation.ui.events.EventsFragment
import com.afshinshahriarifahliani.marvel_characters.presentation.ui.series.SeriesFragment
import com.afshinshahriarifahliani.marvel_characters.presentation.ui.stories.StoriesFragment
import com.google.android.material.tabs.TabLayoutMediator

class CharacterDetailsFragment : Fragment() {
    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: CharacterDetailsFragmentArgs by navArgs()
        val character = args.selectedCharacter
        val fragments = arrayListOf(
            CharacterInfoFragment(character.id),
            ComicsFragment(character.id),
            SeriesFragment(character.id),
            StoriesFragment(character.id),
            EventsFragment(character.id)
        )

        val viewPagerAdapter = SingleCharacterViewPagerAdapter(fragments, requireActivity())
        with(binding) {
            characterViewpager.apply {
                adapter = viewPagerAdapter
            }
            TabLayoutMediator(characterTabLayout, characterViewpager) { tab, position ->
                val listOfTabs =
                    resources.getStringArray(R.array.tab_names)
                tab.text = listOfTabs[position].split(" ")[0]
            }.attach()

            characterViewpager.layoutDirection = ViewPager2.LAYOUT_DIRECTION_LTR
            characterTabLayout.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}