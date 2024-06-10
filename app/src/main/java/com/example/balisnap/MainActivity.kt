package com.example.balisnap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.balisnap.adapter.MainAdapter
import com.example.balisnap.databinding.ActivityMainBinding
import com.example.balisnap.viewmodel.MainViewModel
import com.example.balisnap.utils.Result

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainAdapter = MainAdapter()

        mainViewModel.wisata.observe(this) {wisata ->
            mainAdapter.submitList(wisata)
        }

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
        }

        mainViewModel.getDestination(-8.41465489943872, 115.31360994089808, 5).observe(this) {
            when (it) {
                is Result.Loading -> {

                }

                is Result.Success -> {
                    Log.e("bisa", "${it.data}")
                }

                is Result.Error -> {

                }

                else -> {}
            }
        }
    }
}
