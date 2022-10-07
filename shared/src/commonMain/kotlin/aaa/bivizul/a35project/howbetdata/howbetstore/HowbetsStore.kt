package aaa.bivizul.a35project.howbetdata.howbetstore

import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbets
import aaa.bivizul.a35project.howbetdata.howbetnet.HowbetApi
import aaa.bivizul.a35project.howbetdata.howbetutil.howbetIoDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HowbetsStore {

    private val howbetApi = HowbetApi()
    private val job = SupervisorJob()
    private val scope = CoroutineScope(howbetIoDispatcher + job)

    private val _howbets = MutableStateFlow<List<Howbets>?>(null)
    val howbets: StateFlow<List<Howbets>?> = _howbets.asStateFlow()

    init {
        getHowbets()
    }

    private fun getHowbets() {
        scope.launch {
            val response = howbetApi.getHowbets()
            _howbets.emit(response)
        }
    }

}