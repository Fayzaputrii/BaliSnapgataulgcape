package com.example.balisnap.viewmodel

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balisnap.api.ApiConfig
import com.example.balisnap.repository.DestinationRepository
import com.example.balisnap.response.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (private val userRepositoryUser: DestinationRepository) : ViewModel()  {

}