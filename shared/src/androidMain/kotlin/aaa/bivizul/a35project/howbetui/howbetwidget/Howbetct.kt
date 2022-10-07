package aaa.bivizul.a35project.howbetui.howbetwidget

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

actual fun howbetct(howbetcon: Any, howbetcc: String) {
    val context = howbetcon as Context
    val activity = (context as? Activity)
    val howbetpn = "com.android.chrome"
    val howbetb = CustomTabsIntent.Builder()
        .setShowTitle(false)
        .setInstantAppsEnabled(true)
        .build()
    if (howbetpn != null) {
        howbetb.intent.setPackage(howbetpn)
        howbetb.launchUrl(context, Uri.parse(howbetcc))
    } else {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(howbetcc))
        activity?.startActivity(i)
    }
}