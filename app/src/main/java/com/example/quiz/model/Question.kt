package com.example.quiz.model

class Question(
    val title: String,
    val answerA: String,
    val answerB: String,
    val answerC: String,
    val answerD: String,
    val rightAnswer: Int
) {
    fun checkIfAnswerIsRight(answerPos: Int): Boolean {
        return answerPos == rightAnswer
    }
}

