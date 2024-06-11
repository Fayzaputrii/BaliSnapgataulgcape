package com.example.balisnap.viewmodel

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.balisnap.R
import com.example.balisnap.api.ApiConfig
import com.example.balisnap.repository.DestinationRepository
import com.example.balisnap.response.Data
import com.example.balisnap.response.DestinationResponse
import com.example.balisnap.response.DestinationsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val destinationrepo: DestinationRepository) : ViewModel() {
    private val _namaWisata = MutableLiveData<List<DestinationsItem>>()
    val wisata: LiveData<List<DestinationsItem>> get() = _namaWisata

    companion object {
        private const val TAG = "MainViewModel"
    }

    fun getDestination(lat: Double, lon: Double, radius: Int) =
        destinationrepo.getDestination(lat, lon, radius)


    fun getSearchDestination(image: String, name: String, description: String) {
        val client = ApiConfig.getApiService().getSearchDestination(image, name, description)
        client.enqueue(object : Callback<DestinationResponse> {
            override fun onResponse(
                call: Call<DestinationResponse>, response: Response<DestinationResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.data?.destinations
                    if (responseBody != null) {
                        _namaWisata.postValue(responseBody.filterNotNull())
                    } else {
                        Log.e(TAG, "Response body is null")
                    }
                } else {
                    Log.e(TAG, "Request failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DestinationResponse>, t: Throwable) {
                Log.e(TAG, "Request failed: ${t.message}")
            }
        })
    }
}
