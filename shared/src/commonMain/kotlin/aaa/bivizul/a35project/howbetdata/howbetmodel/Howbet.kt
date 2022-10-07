package aaa.bivizul.a35project.howbetdata.howbetmodel

import kotlinx.serialization.Serializable

@Serializable
data class Howbet(
    val howbetmm: String,
    val howbetsim: String,
    val howbetid: String,
    val howbetl: String,
    val howbett: String,
)