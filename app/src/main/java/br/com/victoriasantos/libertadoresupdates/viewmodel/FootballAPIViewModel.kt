package br.com.victoriasantos.libertadoresupdates.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.domain.Evento
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
                    placar = match.placar,
                    eventos = null
                )
                matches.add(newMatch)
            }
            callback(matches.toTypedArray())
        }
    }

    fun currentMatches(LeagueId: Int, callback: (jogos: Array<Match>, mensagem: String?) -> Unit){
        interactor.currentMatches(LeagueId){ j, flag ->
            if (flag){
                val matches = mutableListOf<Match>()
                val evento = mutableListOf<Evento>()

                j?.forEach { m ->
                    m.eventos?.forEach { e ->
                        val newEvent = Evento(
                            evento_acrescimo = "+${e.evento_acrescimo}'",
                            evento_tempo = "${e.evento_tempo}'",
                            evento_type = e.evento_type,
                            evento_detail = e.evento_detail,
                            evento_assist = e.evento_assist,
                            evento_comments = e.evento_comments,
                            evento_player = e.evento_player,
                            evento_teamName = e.evento_teamName
                        )
                        evento.add(newEvent)
                    }
                    val domain = Match(
                        data = "Data: ${m.data}",
                        rodada = "Rodada: ${m.rodada}",
                        status = "Status da partida: ${m.status}",
                        nome_time_casa = m.nome_time_casa,
                        logo_time_casa = m.logo_time_casa,
                        nome_time_fora = m.nome_time_fora,
                        logo_time_fora = m.logo_time_fora,
                        placar = m.placar,
                        placar_intervalo = "Placar intervalo ${m.placar_intervalo}",
                        placar_prorrogacao = "Placar prorrogação ${m.placar_prorrogacao}",
                        placar_penaltis = "Placar penaltis ${m.placar_penaltis}",
                        tempo = "${m.tempo}'",
                        eventos = evento.toTypedArray()
                    )
                    matches.add(domain)
                }
                callback(matches.toTypedArray(), null)

            }
            else{
                callback(j, app.applicationContext.getString(R.string.empty_current_jogos))
            }
        }
    }

    fun nextMatches(LeagueId: Int, number : Int, callback: (jogos: Array<Match>, mensagem : String?) -> Unit){
        interactor.nextMatches(LeagueId, number){ m, flag ->

            if(flag  == 0){
                callback(m,app.applicationContext.getString(R.string.empty_jogos_futuros))
            }
            else{
                val matches = mutableListOf<Match>()
                m.forEach{ match ->
                    val newMatch = Match(
                        data = "Data: ${match.data}",
                        rodada = "Rodada: ${match.rodada}",
                        nome_time_casa = match.nome_time_casa,
                        logo_time_casa = match.logo_time_casa,
                        nome_time_fora = match.nome_time_fora,
                        logo_time_fora = match.logo_time_fora,
                        eventos = null
                    )
                    matches.add(newMatch)

                }
                callback(matches.toTypedArray(), null)
            }
        }
    }

    fun lastMatches(LeagueId: Int, number: Int, callback: (jogos: Array<Match>) -> Unit){
        interactor.lastMatches(LeagueId, number){ m ->
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
                    placar = match.placar,
                    eventos = null
                )
                matches.add(newMatch)
            }
            callback(matches.toTypedArray())
        }
    }

}