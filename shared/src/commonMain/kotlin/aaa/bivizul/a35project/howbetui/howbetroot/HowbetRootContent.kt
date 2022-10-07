package aaa.bivizul.a35project.howbetui.howbetroot

import aaa.bivizul.a35project.howbetui.howbet.HowbetContent
import aaa.bivizul.a35project.howbetui.howbetmain.MainContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootContent(howbetRootModel: HowbetRootModel, modifier: Modifier = Modifier) {

    val childStack by howbetRootModel.childStack.subscribeAsState()

    Children(
        stack = childStack,
        modifier = modifier,
    ) {
        when (val child = it.instance) {
            is HowbetRootModel.Child.HowbetChild -> HowbetContent(component = child.component)
            is HowbetRootModel.Child.MainChild -> MainContent(howbetMainModel = child.component)
        }
    }

}

