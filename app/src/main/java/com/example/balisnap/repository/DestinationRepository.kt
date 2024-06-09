package com.example.balisnap.repository

import com.example.balisnap.api.ApiService

class DestinationRepository private constructor(
    private val apiService: ApiService,

    ) {
    companion object {
        @Volatile
        private var instance: DestinationRepository? = null
        fun getInstance(
            apiService: ApiService
        ): DestinationRepository =
            instance ?: synchronized(this) {
                instance ?: DestinationRepository(apiService)
            }.also { instance = it }
    }
}