package br.com.victoriasantos.libertadoresupdates.domain

data class DialogFlowRequest(
    val text: String,
    val email: String,
    val sessionId: String
)
