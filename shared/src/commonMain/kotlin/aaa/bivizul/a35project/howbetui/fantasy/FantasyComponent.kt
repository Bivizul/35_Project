package aaa.bivizul.a35project.howbetui.fantasy

import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbets
import aaa.bivizul.a35project.howbetdata.howbetstore.HowbetsStore
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow

class FantasyComponent(
    componentContext: ComponentContext,
    val howbetsStore: HowbetsStore,
    itemId: Int,
) : FantasyModel, ComponentContext by componentContext {

    private val _models = MutableValue(FantasyModel.Model(itemId))
    override val models: Value<FantasyModel.Model> = _models

    override val state: StateFlow<List<Howbets>?> = howbetsStore.howbets
}