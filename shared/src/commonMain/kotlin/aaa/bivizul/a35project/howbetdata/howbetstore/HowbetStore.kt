package aaa.bivizul.a35project.howbetdata.howbetstore

import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbet
import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbetg
import aaa.bivizul.a35project.howbetdata.howbetnet.HowbetApi
import aaa.bivizul.a35project.howbetdata.howbetutil.howbetIoDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HowbetStore {

    private val howbetApi = HowbetApi()
    private val job = SupervisorJob()
    private val scope = CoroutineScope(howbetIoDispatcher + job)

    private val _gethowbetg = MutableStateFlow<Howbetg?>(null)
    val gethowbetg: StateFlow<Howbetg?> = _gethowbetg.asStateFlow()

    fun getHowbetg(howbet: Howbet) {
        scope.launch {
            val response = howbetApi.getHowbetg(howbet)
            _gethowbetg.emit(response)
        }
    }

}