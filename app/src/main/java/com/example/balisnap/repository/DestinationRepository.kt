package com.example.balisnap.repository

import androidx.lifecycle.liveData
import com.example.balisnap.api.ApiService

class DestinationRepository private constructor(
    private val apiService: ApiService,

    ) {
    fun story(token: String) = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getStory("Bearer $token")
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }
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