@file:Suppress("DEPRECATION")

package aaa.bivizul.a35project.howbetdata.howbetutil

import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon.HOWBETACT
import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon.HOWBETKOR
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import com.onesignal.OneSignal
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

actual val howbetIoDispatcher: CoroutineContext = Dispatchers.IO
actual val howbetUiDispatcher: CoroutineContext = Dispatchers.Main

actual fun getHowbetmm(): String {
    val manfachowbet = android.os.Build.MANUFACTURER
    val modelhowbet = android.os.Build.MODEL
    return "$manfachowbet $modelhowbet"
}

actual fun getHowbetsim(howbetcon: Any): String {
    val context = howbetcon as Context
    val telmanhowbet = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return telmanhowbet.simCountryIso
}

actual fun getHowbetid(howbetcon: Any): String {
    val context = howbetcon as Context
    val sharedPreferences = context.getSharedPreferences("appprefhowbet", Context.MODE_PRIVATE)
    var howbetid = sharedPreferences.getString("howbet_key", "nohowbet") ?: "nohowbet"
    if (howbetid == "nohowbet") {
        val dateNow = Date()
        val simpleDateFormat = SimpleDateFormat("yyMMddhhmmssMs")
        val datetime = simpleDateFormat.format(dateNow)
        val randomNum = (10000 until 100000).random()
        howbetid = datetime + randomNum
        sharedPreferences.edit().putString("howbet_key", howbetid).apply()
    }
    return howbetid
}

actual fun getHowbetl(): String {
    return Locale.getDefault().language
}

actual fun getHowbett(): String {
    val howbettz: String = SimpleDateFormat("z", Locale.getDefault()).format(
        Calendar.getInstance(
            TimeZone.getTimeZone("GMT"),
            Locale.getDefault()
        ).time
    ).replace("GMT", "")
    val howbetzone = if (howbettz.contains(":")) howbettz else "default"
    return howbetzone
}

actual fun getHowbetdlg(howbetcon: Any) {
    val context = howbetcon as Context
    val activity = howbetcon as Activity
    AlertDialog.Builder(context).apply {
        setTitle("Connect error")
        setMessage("Please try again later")
        setPositiveButton("Quit") { _, _ ->
            activity.finish()
            System.exit(0)
        }
        setCancelable(true)
    }.create().show()
}

@SuppressLint("MissingPermission")
actual fun checkHowbetnet(howbetcon: Any): Boolean {
    val context = howbetcon as Context
    val conmanhowbet =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netinfhowbet = conmanhowbet.activeNetworkInfo
    return netinfhowbet != null && netinfhowbet.isConnected
}

actual fun sigHowbetoff() {
    OneSignal.disablePush(true)
}

actual fun getHowbetact(howbetact: Any, howbeturl: String) {
    val howbeta = howbetact as Activity
    val howbetc = Class.forName(HOWBETACT)
    val howbeti = Intent(howbeta, howbetc)
    val howbetput = howbeti.putExtra(HOWBETKOR, howbeturl)
    howbeta.startActivity(howbetput)
}

