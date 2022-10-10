package aaa.bivizul.a35project.howbetui.howbetmain

import aaa.bivizul.a35project.howbetdata.howbetstore.HowbetsStore
import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon.TAB_ONE
import aaa.bivizul.a35project.howbetui.betting.BettingComponent
import aaa.bivizul.a35project.howbetui.fantasy.FantasyComponent
import aaa.bivizul.a35project.howbetui.spreads.SpreadsComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

class HowbetMainComponent(
    componentContext: ComponentContext,
) : HowbetMainModel, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()
    private val howbetsStore = HowbetsStore()

    private val stack =
        childStack(
            source = navigation,
            initialConfiguration = Config.Spreads(itemId = TAB_ONE),
            childFactory = ::createChild,
        )

    override val childStack: Value<ChildStack<*, HowbetMainModel.Child>> = stack

    override fun onSpreadsTabClicked(itemId: Int) {
        navigation.bringToFront(Config.Spreads(itemId))
    }

    override fun onBettingTabClicked(itemId: Int) {
        navigation.bringToFront(Config.Betting(itemId))
    }

    override fun onFantasyTabClicked(itemId: Int) {
        navigation.bringToFront(Config.Fantasy(itemId))
    }

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): HowbetMainModel.Child =
        when (config) {
            is Config.Spreads -> HowbetMainModel.Child.SpreadsChild(
                SpreadsComponent(
                    componentContext = componentContext,
                    howbetsStore = howbetsStore,
                    itemId = config.itemId
                )
            )
            is Config.Betting -> HowbetMainModel.Child.BettingChild(
                BettingComponent(
                    componentContext = componentContext,
                    howbetsStore = howbetsStore,
                    itemId = config.itemId
                )
            )
            is Config.Fantasy -> HowbetMainModel.Child.FantasyChild(
                FantasyComponent(
                    componentContext = componentContext,
                    howbetsStore = howbetsStore,
                    itemId = config.itemId
                )
            )
        }

    private sealed class Config : Parcelable {
        @Parcelize
        data class Spreads(val itemId: Int) : Config()

        @Parcelize
        data class Betting(val itemId: Int) : Config()

        @Parcelize
        data class Fantasy(val itemId: Int) : Config()

    }

}