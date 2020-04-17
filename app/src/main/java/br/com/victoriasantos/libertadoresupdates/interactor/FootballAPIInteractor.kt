package br.com.victoriasantos.libertadoresupdates.interactor

import android.content.Context
import br.com.victoriasantos.libertadoresupdates.domain.Match
import br.com.victoriasantos.libertadoresupdates.domain.Team
import br.com.victoriasantos.libertadoresupdates.domain.TeamRanked
import br.com.victoriasantos.libertadoresupdates.repository.FootballAPIRepository
import kotlinx.android.synthetic.main.match_item.*

class FootballAPIInteractor(private val context: Context) {
    private val repository =
        FootballAPIRepository(context, "https://api-football-v1.p.rapidapi.com/v2/")

    fun teams(LeagueId: Int, callback: (times: Array<Team>) -> Unit) {
        repository.teams(LeagueId, callback)
    }

    fun table(LeagueId: Int, callback: (tabela: Array<TeamRanked>) -> Unit) {
        repository.table(LeagueId, callback)
    }

    fun matches(LeagueId: Int, callback: (jogos: Array<Match>) -> Unit) {
        repository.matches(LeagueId, callback)
    }

    fun currentMatches(LeagueId: Int, callback: (jogos: Array<Match>, flag: Boolean) -> Unit){
        repository.currentMatches(LeagueId){jogos ->
            if (jogos==null){
                callback(jogos, false)
            }
            else{
                callback(jogos, true)
            }
        }
    }

    fun nextMatches(LeagueId: Int, number : Int, callback: (jogos: Array<Match>, flag : Int) -> Unit){
        repository.nextMatches(LeagueId, number){m ->
            if(m.isNullOrEmpty()){
                callback(m,0)
            }
            else{
                callback(m,1)
            }
        }
    }

    fun lastMatches(LeagueId: Int, number : Int, callback: (jogos: Array<Match>) -> Unit){
        repository.lastMatches(LeagueId, number){jogos ->
            val matches = mutableListOf<Match>()
            var tempo = "Cancelado"

            jogos.forEach { j ->
                if(!j.tempo.equals("0")) {
                    tempo = j.tempo.toString()
                }
                val domain = Match(
                    tempo = tempo,
                    data = j.data,
                    rodada = j.rodada,
                    status = j.status,
                    nome_time_casa = j.nome_time_casa,
                    logo_time_casa = j.logo_time_casa,
                    nome_time_fora = j.nome_time_fora,
                    logo_time_fora = j.logo_time_fora,
                    placar = j.placar,
                    eventos = null
                )
                matches.add(domain)
            }
            callback(matches.toTypedArray())
        }
    }
}