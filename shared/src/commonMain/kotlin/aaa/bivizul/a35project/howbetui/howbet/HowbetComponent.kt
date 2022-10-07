package aaa.bivizul.a35project.howbetui.howbet

import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbet
import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbetg
import aaa.bivizul.a35project.howbetdata.howbetstore.HowbetStore
import aaa.bivizul.a35project.howbetdata.howbetutil.*
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow

class HowbetComponent(
    componentContext: ComponentContext,
    context: Any,
    howbetStore: HowbetStore,
    private val onReplaceCur: () -> Unit
) : HowbetModel, ComponentContext by componentContext {

    private val _models = MutableValue(HowbetModel.Model(activity = context))
    override val models: Value<HowbetModel.Model> = _models

    override val state: StateFlow<Howbetg?> = howbetStore.gethowbetg

    init {
        try {
            howbetStore.getHowbetg(
                Howbet(
                    getHowbetmm(),
                    getHowbetsim(context),
                    getHowbetid(context),
                    getHowbetl(),
                    getHowbett()
                )
            )
        } catch (e: Exception) {
            getHowbetdlg(context)
        }
    }

    override fun onReplace() {
        onReplaceCur()
    }
}