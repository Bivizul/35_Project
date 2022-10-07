package aaa.bivizul.a35project.howbetui.howbet

import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbetg
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow

interface HowbetModel {

    val models: Value<Model>
    val state: StateFlow<Howbetg?>

    fun onReplace()

    data class Model(
        val activity: Any
    )

}