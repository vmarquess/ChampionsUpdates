package br.com.victoriasantos.libertadoresupdates.domain

data class Match(
    val data: String? = null,
    val rodada: String? = null,
    val status: String? = null,
    val nome_time_casa: String? = null,
    val logo_time_casa: String? = null,
    val nome_time_fora: String? = null,
    val logo_time_fora: String? = null,
    val tempo: String? = null,
    val placar: String? = null
)