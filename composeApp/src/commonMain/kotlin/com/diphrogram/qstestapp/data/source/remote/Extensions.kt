package com.diphrogram.qstestapp.data.source.remote

import com.diphrogram.qstestapp.common.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.isSuccess

fun HttpRequestBuilder.withApiKey() {
	this.parameter(Constants.API_KEY_NAME, Constants.API_KEY)
}

suspend inline fun <reified T> HttpClient.enqueue(
	url: String,
	block: HttpRequestBuilder.() -> Unit = {}
): Result<T> {
	return try {
		val response = this.get {
			url(url)
			withApiKey();
			block()
		}
		if (response.status.isSuccess()) {
			Result.success(response.body<T>())
		} else {
			Result.failure(response.body<Throwable>())
		}
	} catch (e: Exception) {
		Result.failure(e)
	}
}