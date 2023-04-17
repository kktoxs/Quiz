package com.example.quiz.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz.model.Question
import com.example.quiz.model.Repository
import com.example.quiz.model.Wallpaper

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