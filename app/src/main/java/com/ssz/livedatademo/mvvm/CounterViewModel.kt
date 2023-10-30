package com.ssz.livedatademo.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _counter = MutableLiveData<Int>()
    private val _snackbarText = SingleLiveEvent<String>()
    val snackbarText: LiveData<String> = _snackbarText

    val counter: LiveData<Int>
        get() = _counter

    init {
        _counter.value = 0
        Log.d("ssz", "初始化counter值")
    }

    fun incrementCounter() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }


    fun onSnackbarClick() {
        _snackbarText.value = "Snackbar Clicked"
    }


}
