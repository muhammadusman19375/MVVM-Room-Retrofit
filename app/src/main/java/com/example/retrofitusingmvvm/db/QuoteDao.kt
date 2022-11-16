package com.example.retrofitusingmvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes: List<com.example.retrofitusingmvvm.models.Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): List<com.example.retrofitusingmvvm.models.Result>


}