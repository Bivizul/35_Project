package aaa.bivizul.a35project.howbetui.fantasy

import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbets
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow

interface FantasyModel {

    val models: Value<Model>

    val state: StateFlow<List<Howbets>?>

    data class Model(
        val selectedItemId: Int
    )

}