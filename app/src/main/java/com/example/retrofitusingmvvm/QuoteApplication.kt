package com.example.retrofitusingmvvm

import android.app.Application
import com.example.retrofitusingmvvm.api.QuoteService
import com.example.retrofitusingmvvm.api.RetrofitHelper
import com.example.retrofitusingmvvm.db.QuoteDatabase
import com.example.retrofitusingmvvm.repository.QuotesRepository

class QuoteApplication: Application() {
    lateinit var quotesRepository: QuotesRepository

    override fun onCreate() {
        super.onCreate()
        initialize()

    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quotesRepository = QuotesRepository(quoteService,database,applicationContext)
    }
}