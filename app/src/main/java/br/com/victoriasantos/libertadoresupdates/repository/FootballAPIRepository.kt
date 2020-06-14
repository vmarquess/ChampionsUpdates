package br.com.victoriasantos.libertadoresupdates.repository

import android.content.Context
import br.com.victoriasantos.libertadoresupdates.domain.*
import retrofit2.Call
import br.com.victoriasantos.libertadoresupdates.repository.webdto.MatchesAPIDTO
import br.com.victoriasantos.libertadoresupdates.repository.webdto.PlayersDTO
import br.com.victoriasantos.libertadoresupdates.repository.webdto.TableAPIDTO
import br.com.victoriasantos.libertadoresupdates.repository.webdto.TimeAPIDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.Callback



interface TeamInterfaceRepository{
    @GET("teams/league/{LeagueId}")
    fun teams(
        @Path("LeagueId") LeagueId: Int,
        @Header("x-rapidapi-key") apiKey: String = "14567510a0msh03fa53e44f61a66p16eb26jsn439718c20472",
        @Header("x-rapidapi-host") host: String = "api-football-v1.p.rapidapi.com"
    ): Call<TimeAPIDTO>
}

interface TableInterfaceRepository {
    @GET("leagueTable/{LeagueId}")

    fun table(
        @Path("LeagueId") LeagueId: Int,
        @Header("x-rapidapi-key") apiKey: String = "14567510a0msh03fa53e44f61a66p16eb26jsn439718c20472",
        @Header("x-rapidapi-host") host: String = "api-football-v1.p.rapidapi.com"
    ): Call<TableAPIDTO>
}

interface MatchesInterface{

    @GET("fixtures/league/{LeagueId}")
    fun matches(
        @Path("LeagueId") LeagueId: Int,
        @Query("timezone") Timezone: String = "America/Sao_Paulo",
        @Header("x-rapidapi-key") apiKey: String = "14567510a0msh03fa53e44f61a66p16eb26jsn439718c20472",
        @Header("x-rapidapi-host") host: String = "api-football-v1.p.rapidapi.com"
    ):Call<MatchesAPIDTO>
}

interface onGoingMatchesInterface{

    @GET("fixtures/live/{LeagueId}")
    fun currentMatches(
        @Path("LeagueId") LeagueId: Int,
        @Query("timezone") Timezone: String = "America/Sao_Paulo",
        @Header("x-rapidapi-key") apiKey: String = "14567510a0msh03fa53e44f61a66p16eb26jsn439718c20472",
        @Header("x-rapidapi-host") host: String = "api-football-v1.p.rapidapi.com"
    ):Call<MatchesAPIDTO>
}

interface NextMatchesInterface{
    @GET("fixtures/league/{LeagueId}/next/{number}")
    fun matchesProximas(
        @Path("LeagueId") LeagueId: Int,
        @Path("number") number: Int,
        @Query("timezone") Timezone: String = "America/Sao_Paulo",
        @Header("x-rapidapi-key") apiKey: String = "14567510a0msh03fa53e44f61a66p16eb26jsn439718c20472",
        @Header("x-rapidapi-host") host: String = "api-football-v1.p.rapidapi.com"
    ):Call<MatchesAPIDTO>
}

interface PastMatchesInterface{

    @GET("fixtures/league/{LeagueId}/last/{number}")
    fun matchesProximas(
        @Path("LeagueId") LeagueId: Int,
        @Path("number") number: Int,
        @Query("timezone") Timezone: String = "America/Sao_Paulo",
        @Header("x-rapidapi-key") apiKey: String = "14567510a0msh03fa53e44f61a66p16eb26jsn439718c20472",
        @Header("x-rapidapi-host") host: String = "api-football-v1.p.rapidapi.com"
    ):Call<MatchesAPIDTO>
}

interface PlayersInterface{
    @GET("players/squad/{TeamId}/{season}")
    fun players(
        @Path("TeamId") TeamId: Int,
        @Path("season") season: String,
        @Header("x-rapidapi-key") apiKey: String = "14567510a0msh03fa53e44f61a66p16eb26jsn439718c20472",
        @Header("x-rapidapi-host") host: String = "api-football-v1.p.rapidapi.com"
    ): Call<PlayersDTO>

}
class FootballAPIRepository(context: Context, baseUrl: String) : BaseRetrofit(context, baseUrl) {
    private val serviceTeams = retrofit.create(TeamInterfaceRepository::class.java)
    private val serviceTable = retrofit.create(TableInterfaceRepository::class.java)
    private val serviceMatches = retrofit.create(MatchesInterface::class.java)
    private val servicecurrentMatches = retrofit.create(onGoingMatchesInterface::class.java)
    private val serviceNextMatches = retrofit.create(NextMatchesInterface::class.java)
    private val serviceLastMatches = retrofit.create(PastMatchesInterface::class.java)
    private val servicePlayers = retrofit.create(PlayersInterface::class.java)


    fun teams(LeagueId: Int,callback: (times: Array<Team>) -> Unit) {

        serviceTeams.teams(LeagueId).enqueue(object : Callback<TimeAPIDTO> {

            override fun onResponse(call: Call<TimeAPIDTO>, response: Response<TimeAPIDTO>) {
                val times = response.body()?.api?.teams
                val result = mutableListOf<Team>()

                times?.forEach { t ->
                    val domain = Team(
                        name = t.name,
                        logo = t.logo,
                        country = t.country,
                        estadio = t.estadio,
                        fundacao = t.fundacao,
                        id = t.team_id!!
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
                val result = mutableListOf<TeamRanked>()

                teams?.forEach { t ->
                    t.forEach { s ->
                        val saldo: Int? = s.all?.goalsFor?.minus(s.all.goalsAgainst)
                        val domain = TeamRanked(
                            rank = s.rank.toString(),
                            escudo = s.logo,
                            nome = s.teamName,
                            id = s.team_id.toString(),
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
                val result =  mutableListOf<Match>()

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
                        placar = m.score?.fulltime,
                        arbitro = m.arbitro,
                        estadio = m.estadio,
                        eventos = null
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

    fun currentMatches(LeagueId: Int, callback: (jogos: Array<Match>) -> Unit){

        servicecurrentMatches.currentMatches(LeagueId).enqueue(object: Callback<MatchesAPIDTO>{

            override fun onResponse(call: Call<MatchesAPIDTO>, response: Response<MatchesAPIDTO>) {
                val matches = response.body()?.api?.fixtures
                val result = mutableListOf<Match>()
                val evento = mutableListOf<Event>()

                matches?.forEach { m ->
                    m.events?.forEach { e ->
                        val newEvent = Event(
                            evento_acrescimo = e.acrescimo.toString(),
                            evento_tempo = e.tempo.toString(),
                            evento_type = e.type,
                            evento_detail = e.detail,
                            evento_assist = e.assist,
                            evento_comments = e.comments,
                            evento_player = e.player,
                            evento_teamName = e.teamName
                        )
                        evento.add(newEvent)
                    }
                        val domain = Match(
                            data = m.data,
                            rodada = m.round,
                            status = m.status,
                            nome_time_casa = m.homeTeam?.team_name,
                            logo_time_casa = m.awayTeam?.logo,
                            nome_time_fora = m.awayTeam?.team_name,
                            logo_time_fora = m.awayTeam?.logo,
                            placar = m.score?.fulltime,
                            placar_intervalo = m.score?.halftime,
                            placar_prorrogacao = m.score?.extratime,
                            placar_penaltis = m.score?.penalty,
                            tempo = m.tempo.toString(),
                            arbitro = m.arbitro,
                            estadio = m.estadio,
                            eventos = evento.toTypedArray()
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

    fun nextMatches(LeagueId: Int, number: Int, callback: (Proximosjogos: Array<Match>) -> Unit){

        serviceNextMatches.matchesProximas(LeagueId, number).enqueue(object: Callback<MatchesAPIDTO> {

            override fun onResponse(call: Call<MatchesAPIDTO>, response: Response<MatchesAPIDTO>) {
                val matches = response.body()?.api?.fixtures
                val result =  mutableListOf<Match>()

                matches?.forEach { m ->
                    val domain = Match(
                        data = m.data,
                        rodada = m.round,
                        nome_time_casa = m.homeTeam?.team_name,
                        logo_time_casa = m.homeTeam?.logo,
                        nome_time_fora = m.awayTeam?.team_name,
                        logo_time_fora = m.awayTeam?.logo,
                        arbitro = m.arbitro,
                        estadio = m.estadio,
                        eventos = null
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

    fun lastMatches(LeagueId: Int, number: Int, callback: (nextmatches: Array<Match>) -> Unit){

        serviceLastMatches.matchesProximas(LeagueId, number).enqueue(object: Callback<MatchesAPIDTO> {

            override fun onResponse(call: Call<MatchesAPIDTO>, response: Response<MatchesAPIDTO>) {
                val matches = response.body()?.api?.fixtures
                val result =  mutableListOf<Match>()

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
                        placar = m.score?.fulltime,
                        arbitro = m.arbitro,
                        estadio = m.estadio,
                        eventos = null
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

    fun players(id: String, season: String, callback: (players: Array<Player>?) -> Unit){

        servicePlayers.players(id.toInt(), season).enqueue(object: Callback<PlayersDTO>{
            override fun onFailure(call: Call<PlayersDTO>, t: Throwable) {
                callback(null)
        }

            override fun onResponse(call: Call<PlayersDTO>, response: Response<PlayersDTO>) {
              val result = response.body()?.api?.players
                val players = mutableListOf<Player>()

                result?.forEach { p->
                    val player = Player(
                        name = p.name,
                        number = p.number.toString(),
                        position = p.position,
                        age = p.age.toString(),
                        nationality = p.nationality

                    )
                    players.add(player)
                }
                callback(players.toTypedArray())
            }
        })
    }

}