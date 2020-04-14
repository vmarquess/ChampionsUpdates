package br.com.victoriasantos.libertadoresupdates.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.victoriasantos.libertadoresupdates.domain.Time
import br.com.victoriasantos.libertadoresupdates.interactor.FootballAPIInteractor

class FootballAPIViewModel(val app: Application) : AndroidViewModel(app) {
    private val interactor = FootballAPIInteractor(app.applicationContext)

    fun teams(callback: (times: Array<Time>) -> Unit){
        interactor.teams(callback)

    }

}