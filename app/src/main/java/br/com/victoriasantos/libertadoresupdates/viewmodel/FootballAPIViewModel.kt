package br.com.victoriasantos.libertadoresupdates.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.victoriasantos.libertadoresupdates.domain.Match
import br.com.victoriasantos.libertadoresupdates.domain.Team
import br.com.victoriasantos.libertadoresupdates.domain.TeamRanked
import br.com.victoriasantos.libertadoresupdates.interactor.FootballAPIInteractor

class FootballAPIViewModel(val app: Application) : AndroidViewModel(app) {
    private val interactor = FootballAPIInteractor(app.applicationContext)


    fun teams(LeagueId: Int, callback: (times: Array<Team>) -> Unit){
        interactor.teams(LeagueId){ t ->
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

    fun table(LeagueId: Int, callback: (tabela: Array<TeamRanked>) -> Unit){

        interactor.table(LeagueId) { t ->
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
                    saldo = "Saldo de gols: ${team.saldo}",
                    escudo = team.escudo

                )
                tabela.add(newRakedTeam)
            }
            callback(tabela.toTypedArray())
        }
    }

    fun matches(LeagueId: Int, callback: (jogos: Array<Match>) -> Unit){
        interactor.matches(LeagueId){ m ->
            val matches = mutableListOf<Match>()
            m.forEach{ match ->
                val newMatch = Match(
                    data = "Data: ${match.data}",
                    rodada = "Rodada: ${match.rodada}",
                    status = "Status da partida: ${match.status}",
                    nome_time_casa = match.nome_time_casa,
                    logo_time_casa = match.logo_time_casa,
                    nome_time_fora = match.nome_time_fora,
                    logo_time_fora = match.logo_time_fora,
                    tempo = "${match.tempo}'",
                    placar = match.placar
                )
                matches.add(newMatch)

            }
            callback(matches.toTypedArray())
        }
    }

}