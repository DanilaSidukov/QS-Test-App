package com.diphrogram.qstestapp.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val httpClientModule = module {
	factory {
		HttpClient {
			install(ContentNegotiation) {
				json(json = Json {
					ignoreUnknownKeys = true
					useAlternativeNames = false
				})
			}
		}
	}
}