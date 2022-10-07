package aaa.bivizul.a35project.howbetdata.howbetutil

import kotlin.coroutines.CoroutineContext

expect val howbetIoDispatcher: CoroutineContext
expect val howbetUiDispatcher: CoroutineContext

internal expect fun getHowbetmm(): String
internal expect fun getHowbetsim(howbetcon: Any): String
internal expect fun getHowbetid(howbetcon: Any): String
internal expect fun getHowbetl(): String
internal expect fun getHowbett(): String
internal expect fun getHowbetdlg(howbetcon: Any)
internal expect fun checkHowbetnet(howbetcon: Any): Boolean
internal expect fun sigHowbetoff()
internal expect fun getHowbettactoff(howbetcon: Any)