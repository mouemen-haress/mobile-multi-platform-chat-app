package com.example.common.platform_provider

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect object AppContext
