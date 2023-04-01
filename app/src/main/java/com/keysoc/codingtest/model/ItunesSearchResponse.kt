package com.keysoc.codingtest.model

data class ItunesSearchResponse<DATA> (
    val resultCount: Int?= null,
    val results: DATA
)