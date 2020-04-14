package br.com.victoriasantos.libertadoresupdates.interactor

import android.content.Context
import br.com.victoriasantos.libertadoresupdates.domain.Time
import br.com.victoriasantos.libertadoresupdates.repository.FootballAPIRepository

class FootballAPIInteractor(private val context: Context) {
    private val repository = FootballAPIRepository(context, "https://api-football-v1.p.rapidapi.com/v2/")

    fun teams(callback: (times: Array<Time>) -> Unit){
        repository.teams(callback)
    }

}