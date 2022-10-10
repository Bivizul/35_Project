package aaa.bivizul.a35project.howbetui.howbetwidget

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
actual fun Howbetil(howbetiu: String) {

    GlideImage(
        imageModel = howbetiu,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
    )

}