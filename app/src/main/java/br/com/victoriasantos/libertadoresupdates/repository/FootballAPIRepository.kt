package br.com.victoriasantos.libertadoresupdates.repository

import retrofit2.Call
import br.com.victoriasantos.libertadoresupdates.domain.Time
import br.com.victoriasantos.libertadoresupdates.interactor.FootballAPIInteractor
import br.com.victoriasantos.libertadoresupdates.repository.dto.TimeAPIDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.Callback



interface TimeInterfaceRepository{

    @GET("teams/league/1251")
    fun teams(
        @Header("x-rapidapi-key") apiKey: String = "14567510a0msh03fa53e44f61a66p16eb26jsn439718c20472",
        @Header("x-rapidapi-host") host: String = "api-football-v1.p.rapidapi.com"
    ): Call<TimeAPIDTO>
}

class FootballAPIRepository(context: FootballAPIInteractor, baseUrl: String) : BaseRetrofit(context, baseUrl) {
    private val service = retrofit.create(TimeInterfaceRepository::class.java)

    fun teams(callback: (times: Array<Time>) -> Unit){

        service.teams().enqueue(object : Callback<TimeAPIDTO> {

            override fun onResponse(call: Call<TimeAPIDTO>, response: Response<TimeAPIDTO> ){
                val times = response.body()?.api?.teams
               var result = mutableListOf<Time>()

               times?.forEach { t ->
                   val domain = Time(
                       name = t.name,
                       logo = t.logo,
                       country = t.country,
                       estadio = t.estadio,
                       fundacao = t.fundacao
                   )
                   result.add(domain)
               }

               callback(result.toTypedArray())
           }

            override fun onFailure(call: Call<TimeAPIDTO>, t: Throwable ){
                callback(arrayOf())
            }
        })


    }


}

