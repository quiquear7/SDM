package com.uc3m.dresser.repository

import com.uc3m.dresser.api.RetrofitInstance
import com.uc3m.dresser.model.Clima
import retrofit2.Response

class Repository {
    suspend fun getClima(): Response<Clima>{
        return RetrofitInstance.weatherAPI.getClima()
    }

}