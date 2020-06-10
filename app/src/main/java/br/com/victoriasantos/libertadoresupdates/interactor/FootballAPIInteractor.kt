package br.com.victoriasantos.libertadoresupdates.interactor

import android.content.Context
import br.com.victoriasantos.libertadoresupdates.domain.Match
import br.com.victoriasantos.libertadoresupdates.domain.Player
import br.com.victoriasantos.libertadoresupdates.domain.Team
import br.com.victoriasantos.libertadoresupdates.domain.TeamRanked
import br.com.victoriasantos.libertadoresupdates.repository.database.AndroidRoomRepository
import br.com.victoriasantos.libertadoresupdates.repository.FootballAPIRepository

class FootballAPIInteractor(private val context: Context)  {
    private val repositoryWeb = FootballAPIRepository(context, "https://api-football-v1.p.rapidapi.com/v2/")
    private val repositoryLocal =
        AndroidRoomRepository(
            context
        )

    fun teams(LeagueId: Int, callback: (times: Array<Team>) -> Unit) {
        repositoryLocal.showTeam{ result ->
            if(result.isNullOrEmpty()){
                repositoryWeb.teams(LeagueId){ resultWeb ->
                    repositoryLocal.team(1, resultWeb)
                    callback(resultWeb)
                }
            } else{
                callback(result)
            }
        }
    }

    fun table(LeagueId: Int, callback: (tabela: Array<TeamRanked>) -> Unit) {
        repositoryLocal.showTeamRanked{ result ->
            if(result.isNullOrEmpty()){
                repositoryWeb.table(LeagueId){resultWeb ->
                    repositoryLocal.teamRanked(1, resultWeb)
                    callback(resultWeb)
                }
            } else{
                callback(result)
            }
        }
    }

    fun matches(LeagueId: Int, callback: (jogos: Array<Match>) -> Unit) {
        repositoryLocal.showMatch(null){ result ->
            if(result.isNullOrEmpty()){
                repositoryWeb.matches(LeagueId){ resultWeb ->
                    repositoryLocal.matches(1, resultWeb)
                    callback(resultWeb)
                }
            } else{
                callback(result)
            }
        }
    }

    fun currentMatches(LeagueId: Int, callback: (jogos: Array<Match>, flag: Boolean) -> Unit){
        repositoryWeb.currentMatches(LeagueId){ jogos ->
            if (jogos.isNullOrEmpty()){
                callback(jogos, false)
            }
            else{
                callback(jogos, true)
            }
        }
    }

    fun nextMatches(LeagueId: Int, number : Int, callback: (jogos: Array<Match>, flag : Int) -> Unit){
        repositoryLocal.showMatch("Not Started"){ result ->
            if(result.isNullOrEmpty()){
                repositoryWeb.nextMatches(LeagueId, number){m ->
                    if(m.isNullOrEmpty()){
                        callback(m,0)
                    }
                    else{
                        repositoryLocal.matches(1, m)
                        callback(m,1)
                    }
                }
            } else{
                callback(result,1)
            }
        }

    }

    fun lastMatches(LeagueId: Int, number: Int,callback: (jogos: Array<Match>) -> Unit) {
        repositoryLocal.showMatch("Match Finished"){result ->
            if(result.isNullOrEmpty()){
                repositoryWeb.lastMatches(LeagueId,number){ resultWeb ->
                    repositoryLocal.matches(1, resultWeb)
                }
            } else{
                callback(result)
            }
        }
    }

    fun showPlayers(id: String, season: String, callback: (players: Array<Player>?) -> Unit){
        repositoryLocal.showPlayer(id){result ->
            if(result.isNullOrEmpty()){
                repositoryWeb.players(id, season){ resultWeb ->
                    repositoryLocal.playerDatabase(id,1, resultWeb)
                    callback(resultWeb)
                }
            } else{
                callback(result)
            }
        }
    }
}