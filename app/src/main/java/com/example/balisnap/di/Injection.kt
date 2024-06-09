package com.example.balisnap.di

import android.content.Context
import com.example.balisnap.api.ApiConfig
import com.example.balisnap.repository.DestinationRepository

object Injection {
    fun provideRepository(context: Context): DestinationRepository {
        val apiService = ApiConfig.getApiService()
        return DestinationRepository.getInstance(apiService)
    }
}