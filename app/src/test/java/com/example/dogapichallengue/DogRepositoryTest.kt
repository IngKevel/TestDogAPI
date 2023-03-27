package com.example.dogapichallengue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dogapichallengue.data.network.DogApiRepository
import com.example.dogapichallengue.data.network.IDogApi
import com.example.dogapichallengue.domain.models.core.RepositoryResponse
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

class DogRepositoryTest {
    private val mockWebServer = MockWebServer().apply {
        url("/")
        dispatcher = myDispatcher
    }

    private val dogApi = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IDogApi::class.java)

    private val repositoryTest = DogApiRepository(dogApi)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Dog obtained correctly`() {
        runBlocking {
            when (val dogDetail = repositoryTest.getDog()) {
                is RepositoryResponse.Error -> {

                }
                is RepositoryResponse.Success -> {
                    assertEquals("dog1", dogDetail.data.image)
                }
            }
        }
    }
}

val myDispatcher: Dispatcher = object: Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "breeds/image/random/" -> MockResponse().apply { addResponse("dog_response.json") }
            else -> MockResponse().setResponseCode(404)
        }
    }
}

fun MockResponse.addResponse(filePath: String): MockResponse {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        setResponseCode(200)
        setBody(it.readString(StandardCharsets.UTF_8))
    }
    return this
}