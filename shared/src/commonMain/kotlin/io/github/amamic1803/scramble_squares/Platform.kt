package io.github.amamic1803.scramble_squares

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform