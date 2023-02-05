package com.example.lordofthering.home.model.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lordofthering.home.model.datamodel.Doc
import com.example.lordofthering.home.model.datamodel.LotRings
import kotlinx.coroutines.launch

class GetDataViewModel:ViewModel() {

    val dataRetrofit = Retrofit()
    val _dataMovies:MutableLiveData<List<Doc>> by lazy { MutableLiveData<List<Doc>>() }
    val dataMovies:LiveData<List<Doc>> get() = _dataMovies

    fun getData(){

        var listDocs = listOf<Doc>()
        viewModelScope.launch {

            val call = dataRetrofit.getRetrofit().create(Api::class.java).getData()
            val movies = call.body()
            if (call.isSuccessful){

                _dataMovies.value = movies!!.docs
            }
            else{
                Log.e("DATA", call.message().toString())

            }

        }
    }
}