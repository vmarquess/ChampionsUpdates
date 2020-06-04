package br.com.victoriasantos.libertadoresupdates.domain

data class Location(
    var nome: String? = null,
    var estadio: String? = null,
    var latitude: Double? = 0.0,
    var longitude: Double? = 0.0
)