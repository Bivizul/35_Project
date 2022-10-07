package aaa.bivizul.a35project.howbetui.howbetmain

import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon.TAB_ONE
import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon.TAB_THREE
import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon.TAB_TWO
import aaa.bivizul.a35project.howbetui.betting.LiberalizeContent
import aaa.bivizul.a35project.howbetui.fantasy.MarketContent
import aaa.bivizul.a35project.howbetui.spreads.IntroContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.*
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun MainContent(
    howbetMainModel: HowbetMainModel,
    modifier: Modifier = Modifier
) {

    val childStack by howbetMainModel.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Children(
            stack = childStack,
            modifier = Modifier.weight(weight = 1F),
            animation = tabAnimation(),
        ) {
            when (val child = it.instance) {
                is HowbetMainModel.Child.SpreadsChild -> IntroContent(
                    component = child.component,
                    modifier = Modifier.fillMaxSize()
                )
                is HowbetMainModel.Child.BettingChild -> LiberalizeContent(
                    component = child.component,
                    modifier = Modifier.fillMaxSize()
                )
                is HowbetMainModel.Child.FantasyChild -> MarketContent(
                    component = child.component,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 14.dp
        ) {
            BottomNavigationItem(
                selected = activeComponent is HowbetMainModel.Child.SpreadsChild,
                onClick = { howbetMainModel.onSpreadsTabClicked(TAB_ONE) },
                icon = { TextBottomTab(text = "Spreads") },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.onPrimary,
            )

            BottomNavigationItem(
                selected = activeComponent is HowbetMainModel.Child.BettingChild,
                onClick = { howbetMainModel.onBettingTabClicked(TAB_TWO) },
                icon = { TextBottomTab(text = "Betting") },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.onPrimary,
            )

            BottomNavigationItem(
                selected = activeComponent is HowbetMainModel.Child.FantasyChild,
                onClick = { howbetMainModel.onFantasyTabClicked(TAB_THREE) },
                icon = { TextBottomTab(text = "Fantasy") },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.onPrimary,
            )
        }
    }
}

@Composable
fun TextBottomTab(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier.padding(2.dp),
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
    )
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
private fun tabAnimation(): StackAnimation<Any, HowbetMainModel.Child> =
    stackAnimation { child, otherChild, direction ->
        val index = child.instance.index
        val otherIndex = otherChild.instance.index
        val anim = slide()
        if ((index > otherIndex) == direction.isEnter) anim else anim.flipSide()
    }

private val HowbetMainModel.Child.index: Int
    get() =
        when (this) {
            is HowbetMainModel.Child.SpreadsChild -> 0
            is HowbetMainModel.Child.BettingChild -> 1
            is HowbetMainModel.Child.FantasyChild -> 2
        }

@OptIn(ExperimentalDecomposeApi::class)
private fun StackAnimator.flipSide(): StackAnimator =
    StackAnimator { direction, onFinished, content ->
        invoke(
            direction = direction.flipSide(),
            onFinished = onFinished,
            content = content,
        )
    }

@Suppress("OPT_IN_USAGE")
private fun Direction.flipSide(): Direction =
    when (this) {
        Direction.ENTER_FRONT -> Direction.ENTER_BACK
        Direction.EXIT_FRONT -> Direction.EXIT_BACK
        Direction.ENTER_BACK -> Direction.ENTER_FRONT
        Direction.EXIT_BACK -> Direction.EXIT_FRONT
    }