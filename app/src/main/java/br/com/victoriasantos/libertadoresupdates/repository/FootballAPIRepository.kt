package br.com.victoriasantos.libertadoresupdates.repository

import android.content.Context
import br.com.victoriasantos.libertadoresupdates.domain.Match
import retrofit2.Call
import br.com.victoriasantos.libertadoresupdates.domain.Team
import br.com.victoriasantos.libertadoresupdates.domain.TeamRanked
import br.com.victoriasantos.libertadoresupdates.repository.dto.MatchesAPIDTO
import br.com.victoriasantos.libertadoresupdates.repository.dto.TableAPIDTO
import br.com.victoriasantos.libertadoresupdates.repository.dto.TimeAPIDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.Callback


interface TimeInterfaceRepository{

    @GET("teams/league/{LeagueId}")
    fun teams(
        @Path("LeagueId") LeagueId: Int,
        @Header("x-rapidapi-key") apiKey: String = "14567510a0msh03fa53e44f61a66p16eb26jsn439718c20472",
        @Header("x-rapidapi-host") host: String = "api-football-v1.p.rapidapi.com"
    ): Call<TimeAPIDTO>

}


interface TabelaInterfaceRepository {
    @GET("leagueTable/{LeagueId}")

    fun table(
        @Path("LeagueId") LeagueId: Int,
        @Header("x-rapidapi-key") apiKey: String = "14567510a0msh03fa53e44f61a66p16eb26jsn439718c20472",
        @Header("x-rapidapi-host") host: String = "api-football-v1.p.rapidapi.com"
    ): Call<TableAPIDTO>

}

interface JogosInterface{

    @GET("fixtures/league/{LeagueId}")
    fun matches(
        @Path("LeagueId") LeagueId: Int,
        @Query("timezone") Timezone: String = "America/Sao_Paulo",
        @Header("x-rapidapi-key") apiKey: String = "14567510a0msh03fa53e44f61a66p16eb26jsn439718c20472",
        @Header("x-rapidapi-host") host: String = "api-football-v1.p.rapidapi.com"
    ):Call<MatchesAPIDTO>
}

class FootballAPIRepository(context: Context, baseUrl: String) : BaseRetrofit(context, baseUrl) {
    private val serviceTeams = retrofit.create(TimeInterfaceRepository::class.java)
    private val serviceTable = retrofit.create(TabelaInterfaceRepository::class.java)
    private val serviceMatches = retrofit.create(JogosInterface::class.java)

    fun teams(LeagueId: Int,callback: (times: Array<Team>) -> Unit) {

        serviceTeams.teams(LeagueId).enqueue(object : Callback<TimeAPIDTO> {

            override fun onResponse(call: Call<TimeAPIDTO>, response: Response<TimeAPIDTO>) {
                val times = response.body()?.api?.teams
                var result = mutableListOf<Team>()

                times?.forEach { t ->
                    val domain = Team(
                        name = t.name,
                        logo = t.logo,
                        country = t.country,
                        estadio = t.estadio,
                        fundacao = t.fundacao,
                        id = t.team_id.toString()
                    )
                    result.add(domain)
                }

                callback(result.toTypedArray())
            }

            override fun onFailure(call: Call<TimeAPIDTO>, t: Throwable) {
                callback(arrayOf())
            }
        })


    }

    fun table(LeagueId: Int, callback: (standings: Array<TeamRanked>) -> Unit) {

        serviceTable.table(LeagueId).enqueue(object : Callback<TableAPIDTO> {

            override fun onResponse(call: Call<TableAPIDTO>, response: Response<TableAPIDTO>) {
                val teams = response.body()?.api?.standings
                var result = mutableListOf<TeamRanked>()

                teams?.forEach { t ->
                    t.forEach { s ->
                        var saldo: Int? = s.all?.goalsFor?.minus(s.all.goalsAgainst)
                        val domain = TeamRanked(
                            rank = s.rank.toString(),
                            escudo = s.logo,
                            nome = s.teamName,
                            partidas = s.all?.matchsPlayed.toString(),
                            vitorias = s.all?.win.toString(),
                            empates = s.all?.draw.toString(),
                            derrotas = s.all?.lose.toString(),
                            pontos = s.points.toString(),
                            grupo = s.group,
                            saldo = saldo.toString()
                        )
                        result.add(domain)

                    }
                }
                callback(result.toTypedArray())
            }

            override fun onFailure(call: Call<TableAPIDTO>, t: Throwable) {
                callback(arrayOf())
            }
        })
    }

    fun matches(LeagueId: Int, callback: (jogos: Array<Match>) -> Unit){

        serviceMatches.matches(LeagueId).enqueue(object: Callback<MatchesAPIDTO> {

            override fun onResponse(call: Call<MatchesAPIDTO>, response: Response<MatchesAPIDTO>) {
                val matches = response.body()?.api?.fixtures
                var result =  mutableListOf<Match>()

                matches?.forEach { m ->
                    val domain = Match(
                        data = m.data,
                        rodada = m.round,
                        status = m.status,
                        nome_time_casa = m.homeTeam?.team_name,
                        logo_time_casa = m.homeTeam?.logo,
                        nome_time_fora = m.awayTeam?.team_name,
                        logo_time_fora = m.awayTeam?.logo,
                        tempo = m.tempo.toString(),
                        placar = m.score?.fulltime
                    )
                    result.add(domain)

                }
                callback(result.toTypedArray())
            }

            override fun onFailure(call: Call<MatchesAPIDTO>, t: Throwable) {
               callback(arrayOf())
            }



        })

    }
}




