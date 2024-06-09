package com.example.balisnap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.balisnap.databinding.ActivityMainBinding
import com.example.balisnap.viewmodel.MainViewModel
import com.example.balisnap.viewmodel.ViewModelFactory
import com.example.balisnap.utils.Result

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
        }

        viewModel.getDestination(-8.41465489943872, 115.31360994089808, 5).observe(this) {
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
