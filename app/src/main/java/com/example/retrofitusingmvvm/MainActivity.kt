package com.example.retrofitusingmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitusingmvvm.api.QuoteService
import com.example.retrofitusingmvvm.api.RetrofitHelper
import com.example.retrofitusingmvvm.databinding.ActivityMainBinding
import com.example.retrofitusingmvvm.models.QuoteList
import com.example.retrofitusingmvvm.repository.QuotesRepository
import com.example.retrofitusingmvvm.viewmodel.MainViewModel
import com.example.retrofitusingmvvm.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val repository = (application as QuoteApplication).quotesRepository

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            Toast.makeText(this@MainActivity, " "+it.results.size.toString(), Toast.LENGTH_SHORT).show()
        })

    }
}