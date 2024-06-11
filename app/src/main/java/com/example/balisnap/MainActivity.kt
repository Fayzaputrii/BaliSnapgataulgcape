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
import com.example.balisnap.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.getDestination(-8.724451, 115.176827, 1000).observe(this) {
            when (it) {
                is Result.Loading -> {

                }

                is Result.Success -> {

                    val adapter = MainAdapter()
                    adapter.submitList(it.data.data!!.destinations)
                    binding.recyclerview.layoutManager = LinearLayoutManager(this)
                    binding.recyclerview.adapter = adapter

                    Log.e("bisa", "${it.data.data!!.destinations}")
                }

                is Result.Error -> {
                    Log.e("gabisa", "${it.error}")
                }

            }
        }


//        binding.recyclerview.apply {
//            setHasFixedSize(true)
//            this.adapter = destinationAdapter
//        }

        with(binding) {


            searchView.setupWithSearchBar(searchBar)


        }


    }
}
