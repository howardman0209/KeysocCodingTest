package com.keysoc.codingtest.network

import android.util.Log
import com.keysoc.codingtest.util.ITUNES_BASE_URL
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object ItunesGatewayHelper {
    fun searchAlbum(term: String, entity: String = "album"): Disposable {
        return ApiManager.create<ItunesGatewayService>(ITUNES_BASE_URL).searchAlbum(
            term = term,
            entity = entity
        ).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("itunesSearch", "response body: ${it.body()}")
                it.body()?.resultCount
            }, {
                Log.d("itunesSearch", "Error: ${it.message}")
            })
    }
}