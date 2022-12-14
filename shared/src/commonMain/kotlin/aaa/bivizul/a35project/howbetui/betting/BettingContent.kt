package aaa.bivizul.a35project.howbetui.betting

import aaa.bivizul.a35project.howbetui.howbetscreentab.HowbetScreenTab
import aaa.bivizul.a35project.howbetui.howbetwidget.Howbetcp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun LiberalizeContent(
    component: BettingModel,
    modifier: Modifier = Modifier
) {

    val howbetsList by component.state.collectAsState()
    val model by component.models.subscribeAsState()

    if (howbetsList != null) {
        howbetsList?.let { list ->
            list[model.selectedItemId - 1].let { item ->
                HowbetScreenTab(
                    modifier = modifier,
                    item = item
                )
            }
        }
    } else {
        Howbetcp()
    }

}