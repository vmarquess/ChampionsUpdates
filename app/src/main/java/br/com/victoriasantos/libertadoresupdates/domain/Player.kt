package br.com.victoriasantos.libertadoresupdates.domain

import com.google.gson.annotations.SerializedName

data class Player(
    val name: String? = null,
    val number: String? = null,
    val position: String? = null,
    val age: String? = null,
    val nationality: String? = null
)