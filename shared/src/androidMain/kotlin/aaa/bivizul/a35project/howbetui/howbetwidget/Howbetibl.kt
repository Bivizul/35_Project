package aaa.bivizul.a35project.howbetui.howbetwidget

import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
actual fun Howbetibl() {

    val howbetornt = LocalConfiguration.current.orientation
    val howbetiu = when (howbetornt) {
        Configuration.ORIENTATION_PORTRAIT -> Howbetcon.HOWBETBV
        else -> Howbetcon.HOWBETBH
    }

    GlideImage(
        imageModel = howbetiu,
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
    )

}