package aaa.bivizul.a35project.android

import aaa.bivizul.a35project.howbetdata.howbetutil.checkHowbetnet
import aaa.bivizul.a35project.howbetdata.howbetutil.getHowbetdlg
import aaa.bivizul.a35project.howbetui.HowbetRootSetContent
import aaa.bivizul.a35project.howbetui.howbetroot.HowbetRootComponent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (checkHowbetnet(this)) {
            val root = HowbetRootComponent(
                componentContext = defaultComponentContext(),
                context = this@MainActivity
            )
            setContent {
                HowbetRootSetContent(root = root)
            }
        } else {
            getHowbetdlg(this)
        }
    }
}