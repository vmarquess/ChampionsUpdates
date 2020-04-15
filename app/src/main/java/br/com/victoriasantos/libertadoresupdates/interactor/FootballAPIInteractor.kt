package br.com.victoriasantos.libertadoresupdates.interactor

import android.content.Context
import br.com.victoriasantos.libertadoresupdates.domain.Match
import br.com.victoriasantos.libertadoresupdates.domain.Team
import br.com.victoriasantos.libertadoresupdates.domain.TeamRanked
import br.com.victoriasantos.libertadoresupdates.repository.FootballAPIRepository

class FootballAPIInteractor(private val context: Context) {
    private val repository = FootballAPIRepository(context, "https://api-football-v1.p.rapidapi.com/v2/")

    fun teams(callback: (times: Array<Team>) -> Unit){
        repository.teams(callback)
    }

    fun table(callback: (tabela: Array<TeamRanked>) -> Unit){
        repository.table(callback)
    }

    fun matches(callback: (jogos: Array<Match>) -> Unit){
        repository.matches(callback)
    }

}