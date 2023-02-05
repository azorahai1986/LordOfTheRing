package com.example.lordofthering.firebase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lordofthering.home.model.datamodel.Moview
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class FirebaseClass:ViewModel() {
    var db = FirebaseFirestore.getInstance()

    private val _firebaseData:MutableLiveData<MutableList<Moview>> by lazy { MutableLiveData<MutableList<Moview>>() }
    val firebaseData:MutableLiveData<MutableList<Moview>> get() = _firebaseData

    init {

        viewModelScope.launch {

        val datalist = mutableListOf<Moview>()
        db.collection("Movies")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                       // Log.e("Exito", document.id + " => " + document.data)
                        val data = document.toObject(Moview::class.java)
                        datalist.add(data)


                    }

                    _firebaseData.value = datalist

                } else {
                    Log.e("Error", "Error getting documents.", task.exception)
                }
            }
        }
    }

}