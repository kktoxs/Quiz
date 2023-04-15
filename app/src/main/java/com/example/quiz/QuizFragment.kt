package com.example.quiz

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.quiz.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentQuizBinding
    private lateinit var quizList: List<Question>
    private lateinit var currentQuestion: Question
    private lateinit var timer: CountDownTimer
    var questionNum = 0
    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity())[QuizViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startTimeOut()
        viewModel.currentQuizLD.observe(viewLifecycleOwner) {
            quizList = it
            nextQuestion()
        }
        binding.answerAButton.setOnClickListener {
            checkAnswer(it, A)
        }
        binding.answerBButton.setOnClickListener {
            checkAnswer(it, B)
        }
        binding.answerCButton.setOnClickListener {
            checkAnswer(it, C)
        }
        binding.answerDButton.setOnClickListener {
            checkAnswer(it, D)
        }
    }

    private fun checkAnswer(it: View, ansPos: Int) {
        turnOffClickability()
        if (currentQuestion.checkIfAnswerIsRight(ansPos)) {
            it.setBackgroundColor(Color.GREEN)
            (this.requireContext() as MainActivity).incBalance()
        } else {
            it.setBackgroundColor(Color.RED)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            nextQuestion()
            if (it.isAttachedToWindow)
                it.setBackgroundColor(resources.getColor(R.color.grey))
            turnOnClickability()
        }, 750)
    }

    private fun turnOffClickability() {
        binding.answerAButton.isClickable = false
        binding.answerBButton.isClickable = false
        binding.answerCButton.isClickable = false
        binding.answerDButton.isClickable = false
    }

    private fun turnOnClickability() {
        binding.answerAButton.isClickable = true
        binding.answerBButton.isClickable = true
        binding.answerCButton.isClickable = true
        binding.answerDButton.isClickable = true
    }

    private fun startTimeOut() {
        var seconds = arguments?.getInt("seconds")
            ?: throw RuntimeException("unknown difficulty")
        updateTimer(seconds)
        timer = object : CountDownTimer(
            seconds.toLong() * 1000,
            1000
        ) {
            override fun onTick(p0: Long) {
                updateTimer(seconds--)
            }

            override fun onFinish() {
                activity?.onBackPressed()
            }
        }
        timer.start()
    }

    private fun updateTimer(seconds: Int) {
        binding.timer.text = seconds.toString()
    }

    private fun nextQuestion() {
        currentQuestion = quizList[questionNum % quizList.size] //чтобы вопросы шли по кругу
        setQuestion()
        questionNum++
    }

    private fun setQuestion() {
        binding.answerAButton.text = currentQuestion.answerA
        binding.answerBButton.text = currentQuestion.answerB
        binding.answerCButton.text = currentQuestion.answerC
        binding.answerDButton.text = currentQuestion.answerD
        binding.questionTv.text = currentQuestion.title
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        Log.d("quiz","destroy")
    }

    companion object {
        private const val A = 1
        private const val B = 2
        private const val C = 3
        private const val D = 4
    }
}