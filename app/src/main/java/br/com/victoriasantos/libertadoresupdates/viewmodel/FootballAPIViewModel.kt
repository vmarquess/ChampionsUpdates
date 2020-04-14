package br.com.victoriasantos.libertadoresupdates.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.victoriasantos.libertadoresupdates.domain.Time
import br.com.victoriasantos.libertadoresupdates.interactor.FootballAPIInteractor

class FootballAPIViewModel(val app: Application) : AndroidViewModel(app) {
    private val interactor = FootballAPIInteractor(app.applicationContext)

    fun teams(callback: (times: Array<Time>) -> Unit){
        interactor.teams{ t ->
            val times = mutableListOf<Time>()

            t.forEach { team ->
                val newTime = Time(
                    name = "Nome: ${team.name}",
                    logo = team.logo,
                    country = "País: ${team.country}",
                    estadio = "Estádio: ${team.estadio}",
                    fundacao = "Fundação: ${team.fundacao}"

                )
                times.add(newTime)
            }

            callback(times.toTypedArray())
        }

    }

}