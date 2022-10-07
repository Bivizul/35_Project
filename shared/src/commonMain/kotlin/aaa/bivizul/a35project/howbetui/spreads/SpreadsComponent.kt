package aaa.bivizul.a35project.howbetui.spreads

import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbets
import aaa.bivizul.a35project.howbetdata.howbetstore.HowbetsStore
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow

class SpreadsComponent(
    componentContext: ComponentContext,
    val howbetsStore: HowbetsStore,
    itemId: Int,
) : SpreadsModel, ComponentContext by componentContext {

    private val _models = MutableValue(SpreadsModel.Model(itemId))
    override val models: Value<SpreadsModel.Model> = _models

    override val state: StateFlow<List<Howbets>?> = howbetsStore.howbets

}