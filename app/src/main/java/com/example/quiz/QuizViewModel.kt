package com.example.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    private val repository = Repository()

    private val _wallpapersLD = MutableLiveData<List<Wallpaper>>()
    val wallpapersLD: LiveData<List<Wallpaper>>
        get() = _wallpapersLD

    private val _currentQuizLD = MutableLiveData<List<Question>>()
    val currentQuizLD: LiveData<List<Question>>
        get() = _currentQuizLD

    fun getQuestions() {
        val questions = repository.getQuestions()
        _currentQuizLD.postValue(questions)
    }

    fun getWallpapers() {
        val wallpapers = repository.getWallpapers()
        _wallpapersLD.postValue(wallpapers)
    }
}