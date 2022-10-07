package aaa.bivizul.a35project.howbetui.betting

import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbets
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow

interface BettingModel {

    val models: Value<Model>

    val state: StateFlow<List<Howbets>?>

    data class Model(
        val selectedItemId: Int
    )

}