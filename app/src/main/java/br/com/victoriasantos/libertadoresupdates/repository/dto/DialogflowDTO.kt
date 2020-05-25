package br.com.victoriasantos.libertadoresupdates.repository.dto


data class DialogFlowResult(
    val queryResult: QueryResult? = null
)
data class QueryResult(
    val fulfillmentText: String? = null
)

