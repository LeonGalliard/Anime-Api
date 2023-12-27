package com.example.newproject.main

import kotlin.Result


import com.google.gson.annotations.SerializedName

data class TopAnime(
    @SerializedName("request_cache_expiry")
    val requestCacheExpiry: Int,
    @SerializedName("request_cached")
    val requestCached: Boolean,
    @SerializedName("request_hash")
    val requestHash: String,
    @SerializedName("data")
    val top: List<com.example.newproject.main.Result>
)
