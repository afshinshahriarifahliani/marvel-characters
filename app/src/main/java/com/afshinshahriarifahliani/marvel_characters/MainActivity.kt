package com.afshinshahriarifahliani.marvel_characters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.afshinshahriarifahliani.marvel_characters.databinding.ActivityMainBinding
import com.afshinshahriarifahliani.marvel_characters.presentation.adapter.CharacterAdapter
import com.afshinshahriarifahliani.marvel_characters.presentation.adapter.ItemDetailsAdapter
import com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel.MarvelViewModel
import com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel.MarvelViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    @Inject
    lateinit var itemDetailsAdapter: ItemDetailsAdapter

    @Inject
    lateinit var factory: MarvelViewModelFactory
    lateinit var marvelViewModel: MarvelViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        marvelViewModel = ViewModelProvider(this, factory)
            .get(MarvelViewModel::class.java)

        val navView: BottomNavigationView = binding.navView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_characters,
                R.id.navigation_character_info,
                R.id.navigation_character_details,
                R.id.navigation_comics,
                R.id.navigation_series,
                R.id.navigation_events,
                R.id.navigation_stories,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}