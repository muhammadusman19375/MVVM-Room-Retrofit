package com.example.retrofitusingmvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitusingmvvm.api.QuoteService
import com.example.retrofitusingmvvm.models.QuoteList

class QuotesRepository(private val quoteService: QuoteService) {

    private val quotesLiveData = MutableLiveData<QuoteList>()
    val quotes: LiveData<QuoteList>
    get() = quotesLiveData

    suspend fun getQuotes(pageNumber: Int){
        val result = quoteService.getQuotes(pageNumber)
        if(result?.body() != null){
            quotesLiveData.postValue(result.body())
        }

    }

}