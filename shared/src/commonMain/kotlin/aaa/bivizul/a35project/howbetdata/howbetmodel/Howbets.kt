package aaa.bivizul.a35project.howbetdata.howbetmodel

import kotlinx.serialization.Serializable

@Serializable
data class Howbets(
    val howbetin: List<Howbetin>,
    val howbettit: String,
    val id: Int
)