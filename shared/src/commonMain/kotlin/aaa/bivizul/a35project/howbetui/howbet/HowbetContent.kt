package aaa.bivizul.a35project.howbetui.howbet

import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbetar
import aaa.bivizul.a35project.howbetdata.howbetutil.getHowbetact
import aaa.bivizul.a35project.howbetdata.howbetutil.sigHowbetoff
import aaa.bivizul.a35project.howbetui.howbetwidget.Howbetcp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kotlinx.coroutines.delay

@Composable
fun HowbetContent(
    component: HowbetModel,
) {

    val howbetg by component.state.collectAsState()
    val model by component.models.subscribeAsState()

    Howbetcp()

    LaunchedEffect(Unit) {
        delay(1000)
        howbetg?.howbetg?.let {
            if (it == Howbetar.HBNO.hb) {
                component.onReplace()
            } else if (it == Howbetar.HBNP.hb) {
                sigHowbetoff()
                component.onReplace()
            } else {
                getHowbetact(model.activity, it)
            }
        }
    }

}