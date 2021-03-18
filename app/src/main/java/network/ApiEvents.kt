package network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =  "https://my-json-server.typicode.com/bboykot/myJson/"//"https://jsonplaceholder.typicode.com/"//"https://api.spacexdata.com/v3/"//"https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    //.addConverterFactory(ScalarsConverterFactory.create())
    //.addConverterFactory(GsonConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface EventApiService{
    @GET("posts") //"posts"  "launches"
    suspend fun getEvents():List<EventsFromNetwork>
}

object EventApi{
    val retro :EventApiService by lazy {
        retrofit.create(EventApiService::class.java)
    }

}