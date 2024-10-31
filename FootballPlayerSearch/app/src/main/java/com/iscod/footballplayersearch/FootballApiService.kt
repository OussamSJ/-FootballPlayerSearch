import com.iscod.footballplayersearch.PlayerResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Header


interface FootballApiService {
    @GET("players/profiles")
    fun getPlayerProfile(
        @Header("x-rapidapi-host") host: String,
        @Header("x-rapidapi-key") key: String,
        @Query("search") playerName: String
    ): Call<PlayerResponse>
}
