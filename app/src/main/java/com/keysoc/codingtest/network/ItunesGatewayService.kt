package com.keysoc.codingtest.network

import com.keysoc.codingtest.model.ItunesAlbum
import com.keysoc.codingtest.model.ItunesSearchResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//Endpoint
const val searchAPIPath = "search"

interface ItunesGatewayService {
    @GET(searchAPIPath)
    fun searchAlbum(@Query("term") term: String, @Query("entity") entity: String): Observable<Response<ItunesSearchResponse<List<ItunesAlbum>>>>
}