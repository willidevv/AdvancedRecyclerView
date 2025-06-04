package com.willidevv.advancedrecyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TanamanViewModel: ViewModel() {
    var tanamandatabase : TanamanDatabase? = null

    private val _tanaman = MutableLiveData<List<Tanaman>?>()
    val tanaman: MutableLiveData<List<Tanaman>?> get() = _tanaman

    fun setDatabase(db: TanamanDatabase) {
        tanamandatabase = db
    }

    fun getTanaman() {
        viewModelScope.launch(Dispatchers.IO) {
            val allTanaman = tanamandatabase?.tanamanDao()?.getAll()
                _tanaman.postValue(allTanaman)

        }
    }

    fun insertTanaman(tanaman: Tanaman) {
        viewModelScope.launch(Dispatchers.IO) {
            tanamandatabase?.tanamanDao()?.insert(tanaman)
            getTanaman()
        }
    }

    fun getTanamanById(id: Int): LiveData<Tanaman?> {
        val result = MutableLiveData<Tanaman?>()
        viewModelScope.launch(Dispatchers.IO) {
            val tanaman = tanamandatabase?.tanamanDao()?.getById(id)
            result.postValue(tanaman)
        }
        return result
    }


}