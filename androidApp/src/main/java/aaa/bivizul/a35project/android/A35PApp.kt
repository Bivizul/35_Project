package aaa.bivizul.a35project.android

import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon.HOWBETOSAI
import android.app.Application
import com.onesignal.OneSignal

class A35PApp  : Application() {

    override fun onCreate() {
        super.onCreate()

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(HOWBETOSAI)

    }

}