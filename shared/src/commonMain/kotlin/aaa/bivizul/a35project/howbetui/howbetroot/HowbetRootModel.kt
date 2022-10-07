package aaa.bivizul.a35project.howbetui.howbetroot

import aaa.bivizul.a35project.howbetui.howbetmain.HowbetMainModel
import aaa.bivizul.a35project.howbetui.howbet.HowbetModel
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface HowbetRootModel {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class HowbetChild(val component: HowbetModel) : Child()
        class MainChild(val component: HowbetMainModel) : Child()
    }
}