package id.my.anandalukman.sexygirlapp.network

import id.my.anandalukman.sexygirlapp.bean.SexyBean
import retrofit2.http.GET

interface SexyGirlApi {

    @GET("/sexygirl")
    suspend fun getRandomGirl(): SexyBean

    companion object {
        const val BASE_URL = "https://obasaka.herokuapp.com"
    }

}

