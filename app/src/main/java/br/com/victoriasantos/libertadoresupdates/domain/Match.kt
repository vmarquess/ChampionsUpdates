package br.com.victoriasantos.libertadoresupdates.domain

data class Match(
    val data: String? = null,
    val id: String? = null,
    val rodada: String? = null,
    val status: String? = null,
    val nome_time_casa: String? = null,
    val logo_time_casa: String? = null,
    val nome_time_fora: String? = null,
    val logo_time_fora: String? = null,
    val placar: String? = null,
    val placar_intervalo: String? = null,
    val placar_prorrogacao: String? = null,
    val placar_penaltis: String? = null,
    val tempo: String? = null,
    val estadio : String? = null,
    val arbitro : String? = null,
    val eventos: Array<Event>?
)

data class Event(
    val evento_tempo: String? = null,
    val evento_acrescimo: String? = null,
    val evento_teamName: String? = null,
    val evento_player: String? = null,
    val evento_assist: String? = null,
    val evento_type: String? = null,
    val evento_detail: String? = null,
    val evento_comments: String? = null
)