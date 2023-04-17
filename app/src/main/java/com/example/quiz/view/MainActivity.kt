package com.example.quiz.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.quiz.viewModel.QuizViewModel
import com.example.quiz.R
import com.example.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: QuizViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]
        setContentView(binding.root)
        binding.easyButton.setOnClickListener { startQuiz(EASY_TIME) }
        binding.mediumButton.setOnClickListener { startQuiz(MEDIUM_TIME) }
        binding.hardButton.setOnClickListener { startQuiz(HARD_TIME) }
        binding.shopButton.setOnClickListener { openShop() }
        updateBalanceCounter()
    }

    private fun getBalance(): Int {
        return getPreferences(MODE_PRIVATE).getInt(BALANCE, 0)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        updateBalanceCounter()
    }

    private fun updateBalanceCounter() {
        binding.balanceCounterTv.text = getBalance().toString()
    }

    fun incBalance() {
        this.getPreferences(MODE_PRIVATE)
            .edit()
            .putInt(BALANCE, getBalance() + 1)
            .apply()
    }

    fun buyWallpaper(price: Int): Boolean {
        return if (getBalance() >= price) {
            this.getPreferences(MODE_PRIVATE)
                .edit()
                .putInt(BALANCE, getBalance() - price)
                .apply()
            true
        } else {
            Toast.makeText(this, R.string.not_enough_money, Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun startQuiz(seconds: Int) {
        viewModel.getQuestions()
        val quizFragment = QuizFragment()
        quizFragment.apply {
            arguments = Bundle().apply {
                putInt(SECONDS_ARGUMENT, seconds)
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, quizFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun openShop() {
        val shopFragment = ShopFragment()
        shopFragment.apply {
            arguments = Bundle().apply {

            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, shopFragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val SECONDS_ARGUMENT = "seconds"
        private const val BALANCE = "balance"
        private const val EASY_TIME = 45
        private const val MEDIUM_TIME = 30
        private const val HARD_TIME = 15
    }
}