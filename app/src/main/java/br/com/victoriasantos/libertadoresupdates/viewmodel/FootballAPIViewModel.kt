package br.com.victoriasantos.libertadoresupdates.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.victoriasantos.libertadoresupdates.domain.Match
import br.com.victoriasantos.libertadoresupdates.domain.Team
import br.com.victoriasantos.libertadoresupdates.domain.TeamRanked
import br.com.victoriasantos.libertadoresupdates.interactor.FootballAPIInteractor

class FootballAPIViewModel(val app: Application) : AndroidViewModel(app) {
    private val interactor = FootballAPIInteractor(app.applicationContext)

    fun teams(callback: (times: Array<Team>) -> Unit){
        interactor.teams{ t ->
            val times = mutableListOf<Team>()

            t.forEach { team ->
                val newTime = Team(
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

    fun table(callback: (tabela: Array<TeamRanked>) -> Unit){
        interactor.table{ t ->
            val tabela = mutableListOf<TeamRanked>()
            t.forEach { team ->
                val newRakedTeam = TeamRanked(
                    rank = "Posição: ${team.rank}º",
                    nome = "Nome: ${team.nome}",
                    partidas = "Jogos: ${team.partidas}",
                    vitorias = "Vitórias: ${team.vitorias}",
                    empates = "Empates: ${team.empates}",
                    derrotas = "Derrotas: ${team.derrotas}",
                    pontos = "Pontos : ${team.pontos}",
                    grupo = "Grupo: ${team.grupo}",
                    saldo = "Saldo de gols: ${team.saldo}"

                )
                tabela.add(newRakedTeam)
            }
            callback(tabela.toTypedArray())
        }
    }

    fun matches(callback: (jogos: Array<Match>) -> Unit){
        interactor.matches{ m ->

        }
    }

}