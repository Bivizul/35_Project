package aaa.bivizul.a35project.howbetui.howbetmain

import aaa.bivizul.a35project.howbetui.spreads.SpreadsModel
import aaa.bivizul.a35project.howbetui.betting.BettingModel
import aaa.bivizul.a35project.howbetui.fantasy.FantasyModel
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface HowbetMainModel {

    val childStack: Value<ChildStack<*, Child>>

    fun onSpreadsTabClicked(itemId: Int)
    fun onBettingTabClicked(itemId: Int)
    fun onFantasyTabClicked(itemId: Int)

    sealed class Child {
        class SpreadsChild(val component: SpreadsModel) : Child()
        class BettingChild(val component: BettingModel) : Child()
        class FantasyChild(val component: FantasyModel) : Child()
    }

}