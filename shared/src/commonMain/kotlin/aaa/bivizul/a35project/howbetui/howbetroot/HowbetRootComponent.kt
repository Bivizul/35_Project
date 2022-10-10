package aaa.bivizul.a35project.howbetui.howbetroot

import aaa.bivizul.a35project.howbetdata.howbetstore.HowbetStore
import aaa.bivizul.a35project.howbetui.howbet.HowbetComponent
import aaa.bivizul.a35project.howbetui.howbet.HowbetModel
import aaa.bivizul.a35project.howbetui.howbetmain.HowbetMainComponent
import aaa.bivizul.a35project.howbetui.howbetmain.HowbetMainModel
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

class HowbetRootComponent constructor(
    componentContext: ComponentContext,
    private val context: Any
) : HowbetRootModel, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()
    val howbetStore = HowbetStore()

    private val stack =
        childStack(
            source = navigation,
            initialConfiguration = Config.Howbet,
            handleBackButton = true,
            childFactory = ::createChild,
        )

    override val childStack: Value<ChildStack<*, HowbetRootModel.Child>> get() = stack

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): HowbetRootModel.Child =
        when (config) {
            is Config.Howbet -> HowbetRootModel.Child.HowbetChild(itemHowbet(componentContext))
            is Config.Main -> HowbetRootModel.Child.MainChild(itemMain(componentContext))
        }

    private fun itemHowbet(componentContext: ComponentContext): HowbetModel =
        HowbetComponent(
            componentContext = componentContext,
            context = context,
            howbetStore = howbetStore,
            onReplaceCur = {
                navigation.replaceCurrent(Config.Main)
            },
        )

    private fun itemMain(componentContext: ComponentContext): HowbetMainModel =
        HowbetMainComponent(
            componentContext = componentContext,
        )

    private sealed class Config : Parcelable {
        @Parcelize
        object Howbet : Config()

        @Parcelize
        object Main : Config()
    }
}
