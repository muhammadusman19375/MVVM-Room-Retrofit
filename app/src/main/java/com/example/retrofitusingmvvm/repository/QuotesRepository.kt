package com.example.retrofitusingmvvm.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitusingmvvm.api.QuoteService
import com.example.retrofitusingmvvm.db.QuoteDao
import com.example.retrofitusingmvvm.db.QuoteDatabase
import com.example.retrofitusingmvvm.models.QuoteList
import com.example.retrofitusingmvvm.utils.NetworkUtils
import kotlin.text.Typography.quote

class QuotesRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<QuoteList>()
    val quotes: LiveData<QuoteList>
    get() = quotesLiveData


    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getQuotes(pageNumber: Int){

        if(NetworkUtils.isInternetAvaliable(applicationContext)){

            val result = quoteService.getQuotes(pageNumber)
            if(result?.body() != null){
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }

        }

        else{

            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1,1,1,quotes,1,1)
            quotesLiveData.postValue(quoteList)

        }

    }

}