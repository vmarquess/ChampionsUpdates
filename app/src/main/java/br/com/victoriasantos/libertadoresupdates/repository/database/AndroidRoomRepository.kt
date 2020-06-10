package br.com.victoriasantos.libertadoresupdates.repository.database

import android.content.Context
import br.com.victoriasantos.libertadoresupdates.domain.*
import br.com.victoriasantos.libertadoresupdates.repository.roomdto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class AndroidRoomRepository(context: Context) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    private val database = DatabaseHelperImpl(Database.instance(context))

    fun showTeam(callback: (times: Array<Team>?) -> Unit) {
        launch {
            val teams = database.showAllTeam()
            val result = mutableListOf<Team>()

            if (!teams.isNullOrEmpty()) {
                teams.forEach {
                    teams.forEach { t ->
                        val domain = Team(
                            name = t.name,
                            logo = t.logo,
                            country = t.country,
                            estadio = t.estadio,
                            fundacao = t.fundacao,
                            id = t.id
                        )
                        result.add(domain)
                    }
                }
                callback(result.toTypedArray())
            } else {
                callback(null)
            }
        }
    }


    fun team(tipoAcao: Int, times: Array<Team>?) {
        // tipo 1 - inserir
        // tipo 2 - deletar
        // tipo 3 - limpar tabela
        launch {
            if (tipoAcao == 3) {
                database.deleteAllTeamTable()
            } else if (!times.isNullOrEmpty()) {
                times.forEach { team ->
                    val domain = TeamEntity(
                        name = team.name,
                        logo = team.logo,
                        country = team.country,
                        estadio = team.estadio,
                        fundacao = team.fundacao,
                        id = team.id!!
                    )
                    if (tipoAcao == 1) {
                        database.insertTeam(domain)
                    } else if (tipoAcao == 2) {
                        database.deleteTeam(domain)
                    }
                }
            }
        }
    }

    fun showTeamRanked(callback: (times: Array<TeamRanked>?) -> Unit) {

        launch {
            val teams = database.showAllTeamRanked()
            val result = mutableListOf<TeamRanked>()

            if (!teams.isNullOrEmpty()) {
                teams.forEach { t ->
                    val domain = TeamRanked(
                        derrotas = t.derrotas,
                        escudo = t.escudo,
                        empates = t.empates,
                        grupo = t.grupo,
                        id = t.id!!.toString(),
                        nome = t.nome,
                        rank = t.rank.toString(),
                        partidas = t.partidas,
                        vitorias = t.vitorias,
                        pontos = t.pontos,
                        saldo = t.saldo
                    )
                    result.add(domain)
                }
                callback(result.toTypedArray())
            } else {
                callback(null)
            }
        }
    }

    fun teamRanked(tipoAcao: Int, timesRank: Array<TeamRanked>) {
        // tipo 1 - inserir
        // tipo 2 - deletar
        timesRank.forEach { team ->
            val domain = TeamRankedEntity(
                derrotas = team.derrotas,
                escudo = team.escudo,
                empates = team.empates,
                grupo = team.grupo,
                id = team.id!!.toInt(),
                nome = team.nome,
                rank = team.rank!!.toInt(),
                partidas = team.partidas,
                vitorias = team.vitorias,
                pontos = team.pontos,
                saldo = team.saldo
            )
            launch {
                if (tipoAcao == 1) {
                    database.insertTeamRanked(domain)
                } else if (tipoAcao == 2) {
                    database.deleteTeamRanked(domain)
                }
            }
        }
    }

    fun showPlayer(id: String, callback: (players: Array<Player>?) -> Unit) {
        launch {
            val player = database.showPlayerByTeam(id)
            val result = mutableListOf<Player>()

            if (!player.isNullOrEmpty()) {
                player.forEach { p ->
                    val domain = Player(
                        name = p.name,
                        position = p.position,
                        number = p.number,
                        age = p.age,
                        nationality = p.nationality
                    )
                    result.add(domain)
                }
                callback(result.toTypedArray())
            } else {
                callback(null)
            }
        }
    }

    fun playerDatabase(id_team: String, tipoAcao: Int, player: Array<Player>?) {
        if (!player.isNullOrEmpty()) {
            player.forEach { p ->
                val domain = PlayerEntity(
                    name = p.name,
                    position = p.position,
                    number = p.number,
                    age = p.age,
                    id_team = id_team.toInt(),
                    nationality = p.nationality
                )
                launch {
                    if (tipoAcao == 1) {
                        database.insertPlayer(domain)
                    } else if (tipoAcao == 2) {
                        database.deletePlayer(domain)
                    }
                }
            }
        }
    }

    fun showMatch(status: String?, callback: (match: Array<Match>?) -> Unit) {
        var match: Array<MatchEntity>
        val result = mutableListOf<Match>()
        launch {
            if (status.isNullOrBlank()) {
                match = database.showAllMatch()
            } else {
                match = database.showAMatchbyStatus(status)
            }

            if (!match.isNullOrEmpty()) {
                match.forEach { m ->
                    val domain = Match(
                        data = m.data,
                        rodada = m.rodada,
                        status = m.status,
                        nome_time_casa = m.nome_time_casa,
                        logo_time_casa = m.logo_time_casa,
                        nome_time_fora = m.nome_time_fora,
                        logo_time_fora = m.logo_time_fora,
                        placar = m.placar,
                        placar_intervalo = m.placar_intervalo,
                        placar_penaltis = m.placar_penaltis,
                        placar_prorrogacao = m.placar_prorrogacao,
                        tempo = m.tempo,
                        estadio = m.estadio,
                        arbitro = m.arbitro,
                        eventos = null
                    )
                    result.add(domain)
                }
                callback(result.toTypedArray())
            } else {
                callback(null)
            }
        }
    }

    fun matches(tipoAcao: Int, match: Array<Match>?) {
        // tipo 1 - inserir
        // tipo 2 - deletar
        // tipo 3 - update
        launch {
            if (tipoAcao == 3) {
                database.deleteAllMatchTable()
            } else if (!match.isNullOrEmpty()) {
                match.forEach { m ->
                    val domain = MatchEntity(
                        data = m.data,
                        rodada = m.rodada,
                        status = m.status,
                        nome_time_casa = m.nome_time_casa,
                        logo_time_casa = m.logo_time_casa,
                        nome_time_fora = m.nome_time_fora,
                        logo_time_fora = m.logo_time_fora,
                        placar = m.placar,
                        placar_intervalo = m.placar_intervalo,
                        placar_penaltis = m.placar_penaltis,
                        placar_prorrogacao = m.placar_prorrogacao,
                        tempo = m.tempo,
                        estadio = m.estadio,
                        arbitro = m.arbitro
                    )
                    if (tipoAcao == 1) {
                        database.insertMatch(domain)
                    } else if (tipoAcao == 2) {
                        database.deleteMatch(domain)
                    } else if (tipoAcao == 4) {
                        database.updateMatch(domain)
                    }
                }
            }
        }
    }
}




