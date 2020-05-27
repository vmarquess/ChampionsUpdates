package br.com.victoriasantos.libertadoresupdates.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.domain.*
import br.com.victoriasantos.libertadoresupdates.interactor.FootballAPIInteractor

class FootballAPIViewModel(val app: Application) : AndroidViewModel(app) {
    private val interactor = FootballAPIInteractor(app.applicationContext)


    fun teams(LeagueId: Int, callback: (times: Array<Team>) -> Unit){
        interactor.teams(LeagueId){ t ->
            val times = mutableListOf<Team>()

            var fundacao: String = app.applicationContext.getString(R.string.desconhecido)
            var estadio = app.applicationContext.getString(R.string.desconhecido)

            t.forEach { team ->
                if(!team.fundacao.isNullOrBlank()){
                    fundacao = team.fundacao
                }
                if(!team.estadio.isNullOrBlank()){
                    estadio = team.estadio
                }

                val newTime = Team(
                    name = app.applicationContext.getString(R.string.nome) + team.name,
                    logo = team.logo,
                    country = app.applicationContext.getString(R.string.pais) + team.country,
                    estadio = app.applicationContext.getString(R.string.estadio) + estadio,
                    fundacao = app.applicationContext.getString(R.string.fundacao) + fundacao,
                    id = team.id
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
                    rank = app.applicationContext.getString(R.string.posicao) + team.rank + "º",
                    nome = app.applicationContext.getString(R.string.nome) + team.nome,
                    partidas = app.applicationContext.getString(R.string.jogos) + team.partidas,
                    vitorias = app.applicationContext.getString(R.string.vitorias) + team.vitorias,
                    empates = app.applicationContext.getString(R.string.empates) + team.empates,
                    derrotas = app.applicationContext.getString(R.string.derrotas) + team.derrotas,
                    pontos = app.applicationContext.getString(R.string.pontos) + team.pontos,
                    grupo = team.grupo,
                    saldo = app.applicationContext.getString(R.string.saldo_gols) + team.saldo,
                    escudo = team.escudo

                )
                tabela.add(newRakedTeam)
            }
            callback(tabela.toTypedArray())
        }
    }

    fun matches(LeagueId: Int, callback: (jogos: Array<Match>) -> Unit){
        interactor.matches(LeagueId){ m ->

            var arbitro = app.applicationContext.getString(R.string.indefinido)
            var estadio = app.applicationContext.getString(R.string.indefinido)

            val matches = mutableListOf<Match>()

            m.forEach{ match ->

                if(!match.arbitro.isNullOrBlank()){
                    arbitro = match.arbitro
                }
                if(!match.estadio.isNullOrBlank()){
                    estadio = match.estadio
                }

                val newMatch = Match(
                    data = app.applicationContext.getString(R.string.data) + match.data,
                    rodada = app.applicationContext.getString(R.string.rodada) + match.rodada,
                    status = app.applicationContext.getString(R.string.status_partida) + match.status,
                    nome_time_casa = match.nome_time_casa,
                    logo_time_casa = match.logo_time_casa,
                    nome_time_fora = match.nome_time_fora,
                    logo_time_fora = match.logo_time_fora,
                    tempo = match.tempo,
                    placar = match.placar,
                    arbitro = app.applicationContext.getString(R.string.arbitro) + arbitro,
                    estadio = app.applicationContext.getString(R.string.estadio) + estadio,
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

                var arbitro = app.applicationContext.getString(R.string.indefinido)
                var estadio = app.applicationContext.getString(R.string.indefinido)

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

                    if(!m.arbitro.isNullOrBlank()){
                        arbitro = m.arbitro
                    }
                    if(!m.estadio.isNullOrBlank()){
                        estadio = m.estadio
                    }

                    val domain = Match(
                        data = app.applicationContext.getString(R.string.data)+ m.data,
                        rodada = app.applicationContext.getString(R.string.rodada) + m.rodada,
                        status = app.applicationContext.getString(R.string.status_partida) + m.status,
                        nome_time_casa = m.nome_time_casa,
                        logo_time_casa = m.logo_time_casa,
                        nome_time_fora = m.nome_time_fora,
                        logo_time_fora = m.logo_time_fora,
                        placar = m.placar,
                        placar_intervalo = app.applicationContext.getString(R.string.placar_intervalo) + m.placar_intervalo,
                        placar_prorrogacao = app.applicationContext.getString(R.string.placar_prorrogacao) + m.placar_prorrogacao,
                        placar_penaltis = app.applicationContext.getString(R.string.placar_penaltis) + m.placar_penaltis,
                        tempo = "${m.tempo}'",
                        arbitro = app.applicationContext.getString(R.string.arbitro) + arbitro + " ",
                        estadio = app.applicationContext.getString(R.string.estadio) + estadio,
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
                var arbitro = app.applicationContext.getString(R.string.indefinido)
                var estadio = app.applicationContext.getString(R.string.indefinido)

                val matches = mutableListOf<Match>()

                m.forEach{ match ->

                    if(!match.arbitro.isNullOrBlank()){
                        arbitro = match.arbitro
                    }
                    if(!match.estadio.isNullOrBlank()){
                        estadio = match.estadio
                    }

                    val newMatch = Match(
                        data = app.applicationContext.getString(R.string.data) + match.data,
                        rodada = app.applicationContext.getString(R.string.rodada) + match.rodada,
                        nome_time_casa = match.nome_time_casa,
                        logo_time_casa = match.logo_time_casa,
                        nome_time_fora = match.nome_time_fora,
                        logo_time_fora = match.logo_time_fora,
                        arbitro = app.applicationContext.getString(R.string.arbitro) + arbitro,
                        estadio = app.applicationContext.getString(R.string.estadio) + estadio,
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

            var tempo = app.applicationContext.getString(R.string.cancelado)
            var arbitro = app.applicationContext.getString(R.string.indefinido)
            var estadio = app.applicationContext.getString(R.string.indefinido)

            val matches = mutableListOf<Match>()

            m.forEach{ match ->

                if(!match.tempo.equals("0")) {
                    tempo = match.tempo.toString()
                }
                if(!match.arbitro.isNullOrBlank()){
                    arbitro = match.arbitro
                }
                if(!match.estadio.isNullOrBlank()){
                    estadio = match.estadio
                }

                val newMatch = Match(
                    data = app.applicationContext.getString(R.string.data) + match.data,
                    rodada = app.applicationContext.getString(R.string.rodada) + match.rodada,
                    status = app.applicationContext.getString(R.string.status_partida) + match.status,
                    nome_time_casa = match.nome_time_casa,
                    logo_time_casa = match.logo_time_casa,
                    nome_time_fora = match.nome_time_fora,
                    logo_time_fora = match.logo_time_fora,
                    tempo = "${tempo}'",
                    placar = match.placar,
                    arbitro = app.applicationContext.getString(R.string.arbitro) + arbitro,
                    estadio = app.applicationContext.getString(R.string.estadio) + estadio,
                    eventos = null
                )
                matches.add(newMatch)
            }
            callback(matches.toTypedArray())
        }
    }

    fun showPlayers(id: String, season: Int, callback: (players: Array<Player>?) -> Unit){
        interactor.showPlayers(id, season){ players ->
            val aux = mutableListOf<Player>()

            players?.forEach { p->
                val player = Player(
                    name = app.applicationContext.getString(R.string.nome) + p.name + "\n",
                    number = app.applicationContext.getString(R.string.numero) + p.number + "\n",
                    age = app.applicationContext.getString(R.string.idade) + p.age + "\n",
                    nationality = app.applicationContext.getString(R.string.nacionalidade) + p.nationality + "\n\n",
                    position = app.applicationContext.getString(R.string.posicao)+ p.position + "\n"
                )
                aux.add(player)
            }
            callback(aux.toTypedArray())
        }
    }

}