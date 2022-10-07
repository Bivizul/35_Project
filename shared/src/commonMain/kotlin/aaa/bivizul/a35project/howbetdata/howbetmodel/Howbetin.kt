package aaa.bivizul.a35project.howbetdata.howbetmodel

import kotlinx.serialization.Serializable

@Serializable
data class Howbetin(
    val howbetimg: String,
    val howbetsubdesc: String,
    val howbetsubtit: String,
    val id: Int
)