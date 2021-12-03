package com.picpay.desafio.android.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule : TestWatcher() {

    private val server = MockWebServer()

    override fun starting(description: Description?) {
        super.starting(description)

        server.start(port = SERVER_PORT)
        server.dispatcher = getDispatcher()
    }

    override fun finished(description: Description?) {
        super.finished(description)
        server.shutdown()
    }

    private fun getDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when(request.path) {
                    "/users" -> successResponse
                    else -> errorResponse
                }
            }
        }
    }

    companion object {
        private const val SERVER_PORT = 3000

        private val successResponse by lazy {
            val body =
                "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"

            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }

}