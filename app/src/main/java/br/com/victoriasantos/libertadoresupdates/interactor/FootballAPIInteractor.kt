package br.com.victoriasantos.libertadoresupdates.interactor

import android.content.Context
import br.com.victoriasantos.libertadoresupdates.domain.Match
import br.com.victoriasantos.libertadoresupdates.domain.Player
import br.com.victoriasantos.libertadoresupdates.domain.Team
import br.com.victoriasantos.libertadoresupdates.domain.TeamRanked
import br.com.victoriasantos.libertadoresupdates.repository.database.AndroidRoomRepository
import br.com.victoriasantos.libertadoresupdates.repository.FootballAPIRepository
import java.text.SimpleDateFormat
import java.util.*

class FootballAPIInteractor(private val context: Context) {
    private val repositoryWeb =
        FootballAPIRepository(context, "https://api-football-v1.p.rapidapi.com/v2/")
    private val repositoryLocal =
        AndroidRoomRepository(
            context
        )

    fun teams(LeagueId: Int, callback: (times: Array<Team>) -> Unit) {
        repositoryLocal.showTeam { result ->
            if (result.isNullOrEmpty()) {
                repositoryWeb.teams(LeagueId) { resultWeb ->
                    repositoryLocal.team(1, resultWeb)
                    callback(resultWeb)
                }
            } else {
                callback(result)
            }
        }
    }

    fun table(LeagueId: Int, update: Int, callback: (tabela: Array<TeamRanked>, date: String?) -> Unit) {
        if (update == 0) {
            repositoryLocal.showTeamRanked { result, date ->
                if (result.isNullOrEmpty()) {
                    repositoryWeb.table(LeagueId) { resultWeb ->
                        repositoryLocal.teamRanked(1, resultWeb)
                        callback(resultWeb, date)
                    }
                } else {
                    callback(result, date)
                }
            }
        } else {
            repositoryWeb.table(LeagueId) { resultWeb ->
                repositoryLocal.teamRanked(1, resultWeb)
                callback(resultWeb, SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date().time))

            }
        }
    }

        fun matches(LeagueId: Int, update: Int, callback: (jogos: Array<Match>, date: String?) -> Unit) {
            if (update == 0) {
                repositoryLocal.showMatch(null) { result, date ->
                    if (result.isNullOrEmpty()) {
                        repositoryWeb.matches(LeagueId) { resultWeb ->
                            repositoryLocal.matches(1, resultWeb)
                            callback(resultWeb, date)
                        }
                    } else {
                        callback(result, date)
                    }
                }
            } else {
                repositoryWeb.matches(LeagueId) { resultWeb ->
                    repositoryLocal.matches(1, resultWeb)
                    callback(resultWeb, SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date().time))
                }
            }
        }

        fun currentMatches(LeagueId: Int, callback: (jogos: Array<Match>, flag: Boolean) -> Unit) {
            repositoryWeb.currentMatches(LeagueId) { jogos ->
                if (jogos.isNullOrEmpty()) {
                    callback(jogos, false)
                } else {
                    callback(jogos, true)
                }
            }
        }

        fun nextMatches(LeagueId: Int, update: Int, number: Int, callback: (jogos: Array<Match>, flag: Int, date: String) -> Unit) {
            if (update == 0) {
                repositoryLocal.showMatch("Not Started") { result, date ->
                    if (result.isNullOrEmpty()) {
                        repositoryWeb.nextMatches(LeagueId, number) { m ->
                            if (m.isNullOrEmpty()) {
                                callback(m, 0, date!!)
                            } else {
                                repositoryLocal.matches(1, m)
                                callback(m, 1, date!!)
                            }
                        }
                    } else {
                        callback(result, 1, date!!)
                    }
                }
            } else {
                repositoryWeb.nextMatches(LeagueId, number) { m ->
                    if (m.isNullOrEmpty()) {
                        callback(m, 0, SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date().time))
                    } else {
                        repositoryLocal.matches(1, m)
                        callback(m, 1, SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date().time))
                    }
                }
            }

        }

        fun lastMatches(
            LeagueId: Int,
            update: Int,
            number: Int,
            callback: (jogos: Array<Match>, date: String?) -> Unit
        ) {
            if (update == 0) {
                repositoryLocal.showMatch("Match Finished") { result, date ->
                    if (result.isNullOrEmpty()) {
                        repositoryWeb.lastMatches(LeagueId, number) { resultWeb ->
                            repositoryLocal.matches(1, resultWeb)
                            callback(resultWeb, date)
                        }
                    } else {
                        callback(result, date)
                    }
                }
            } else {
                repositoryWeb.lastMatches(LeagueId, number) { resultWeb ->
                    repositoryLocal.matches(1, resultWeb)
                    callback(resultWeb, SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date().time))
                }
            }
        }

        fun showPlayers(id: String, season: String, callback: (players: Array<Player>?) -> Unit) {
            repositoryLocal.showPlayer(id) { result ->
                if (result.isNullOrEmpty()) {
                    repositoryWeb.players(id, season) { resultWeb ->
                        repositoryLocal.playerDatabase(id, 1, resultWeb)
                        callback(resultWeb)
                    }
                } else {
                    callback(result)
                }
            }
        }
    }

