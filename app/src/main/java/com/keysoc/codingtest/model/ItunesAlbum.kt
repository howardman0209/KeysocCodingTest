package com.keysoc.codingtest.model

import java.math.BigDecimal

data class ItunesAlbum(
    val wrapperType: String? = null,
    val collectionType: String? = null,
    val artistId: Long? = null,
    val collectionId: Long? = null,
    val amgArtistId: Long? = null,
    val artistName: String? = null,
    val collectionName: String? = null,
    val collectionCensoredName: String? = null,
    val artistViewUrl: String? = null,
    val collectionViewUrl: String? = null,
    val artworkUrl60: String? = null,
    val artworkUrl100: String? = null,
    val collectionPrice: BigDecimal? = null,
    val collectionExplicitness: String? = null,
    val trackCount: Int? = null,
    val copyright: String? = null,
    val country: String? = null,
    val currency: String? = null,
    val releaseDate: String? = null,
    val primaryGenreName: String? = null,
)