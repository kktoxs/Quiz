package com.example.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {
    private val repository = Repository()

    val wallpapersLD = MutableLiveData<List<Wallpaper>>()
    val currentQuizLD = MutableLiveData<List<Question>>()

    fun getQuestions(){
        val questions = repository.getQuestions()
        currentQuizLD.postValue(questions)
    }

    fun getWallpapers(){
        val wallpapers = repository.getWallpapers()
        wallpapersLD.postValue(wallpapers)
    }
}