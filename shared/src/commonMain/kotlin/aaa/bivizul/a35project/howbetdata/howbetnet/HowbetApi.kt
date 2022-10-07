package aaa.bivizul.a35project.howbetdata.howbetnet

import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbet
import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbetg
import aaa.bivizul.a35project.howbetdata.howbetmodel.Howbets
import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon.HOWBETBASEURL
import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon.HOWBETSURL
import aaa.bivizul.a35project.howbetdata.howbetutil.Howbetcon.HOWBETGURL
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class HowbetApi {

    val howbethc = HttpClient(CIO) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
    }

    private fun HttpRequestBuilder.base(path: String) {
        url {
            takeFrom(HOWBETBASEURL)
            encodedPath = path
        }
    }

    suspend fun getHowbets(): List<Howbets> {
        val gethowbetsurl = HOWBETSURL
        val howbethr = howbethc.get { base(gethowbetsurl) }
        val gethowbetsbody = howbethr.body<List<Howbets>>()
        return gethowbetsbody
    }

    suspend fun getHowbetg(howbet: Howbet): Howbetg {
        val gethowbeturl = HOWBETGURL
        val howbethr = howbethc.post {
            base(gethowbeturl)
            contentType(ContentType.Application.Json)
            setBody(howbet)
        }
        val gethowbetbody = howbethr.body<Howbetg>()
        return gethowbetbody
    }

}