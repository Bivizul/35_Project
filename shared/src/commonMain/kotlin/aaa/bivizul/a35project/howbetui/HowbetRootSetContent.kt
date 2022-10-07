package aaa.bivizul.a35project.howbetui

import aaa.bivizul.a35project.howbetui.howbetroot.HowbetRootComponent
import aaa.bivizul.a35project.howbetui.howbetroot.RootContent
import aaa.bivizul.a35project.howbetui.howbetwidget.Howbetibl
import aaa.bivizul.a35project.howbetui.howbettheme.MyApplicationTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HowbetRootSetContent(
    root: HowbetRootComponent
) {
    MyApplicationTheme {
        Howbetibl()
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            RootContent(root)
        }
    }
}
