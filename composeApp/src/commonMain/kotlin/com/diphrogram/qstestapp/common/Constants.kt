package com.diphrogram.qstestapp.common

import com.diphrogram.qstestapp.BuildKonfig

object Constants {
	const val BASE_URL = "https://api.themoviedb.org/3/"
	const val API_KEY_NAME = "api_key"
	val API_KEY = BuildKonfig.API_KEY_TMDB
	const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
	const val DEFAULT_AVATAR = "drawable/ic_avatar.png"
	const val NO_POSTER = "drawable/poster_unavaliable.png"

	val EMAIL_REGULAR_EXPRESSION = """
            (?:[a-zA-Z0-9!#${'$'}%&'*+/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#${'$'}%&'*+/=?^_`{|}~-]+)*|"
            (?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")
            @(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*
            [a-zA-Z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:
            (2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-zA-Z0-9-]*[a-zA-Z0-9]:
            (?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])
        """.trimIndent().replace("\n", "")
}

