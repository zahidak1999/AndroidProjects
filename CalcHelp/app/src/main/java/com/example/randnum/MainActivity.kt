package com.example.randnum

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : Activity() {
    private val numberRequest = 1
    private val numberRequest2 = 2

    private lateinit var number1TextView: TextView
    private lateinit var number2TextView: TextView
    private lateinit var answerEditText: EditText
    private lateinit var upperLimitEditText: EditText
    private lateinit var addButton: Button
    private lateinit var multiplyButton: Button

    private var upperLimit: Int = 10 // Default upper limit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number1TextView = findViewById(R.id.number1TextView)
        number2TextView = findViewById(R.id.number2TextView)
        answerEditText = findViewById(R.id.answerEditText)
        upperLimitEditText = findViewById(R.id.upperLimitEditText)
        addButton = findViewById(R.id.addButton)
        multiplyButton = findViewById(R.id.multiplyButton)

        // Set default values
        number1TextView.text = "3"
        number2TextView.text = "5"
        answerEditText.setText("8")
        upperLimitEditText.setText("10")

        addButton.setOnClickListener { checkAnswer(isAddition = true) }
        multiplyButton.setOnClickListener { checkAnswer(isAddition = false) }
    }

    private fun checkAnswer(isAddition: Boolean) {
        val num1 = number1TextView.text.toString().toInt()
        val num2 = number2TextView.text.toString().toInt()
        val userAnswer = answerEditText.text.toString().toIntOrNull()

        if (userAnswer == null) {
            Toast.makeText(this, "Please write valid numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val correctAnswer = if (isAddition) num1 + num2 else num1 * num2

        if (userAnswer == correctAnswer) {
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "${getString(R.string.incorrect)} $correctAnswer", Toast.LENGTH_SHORT).show()
        }

        generateNewNumbers()
    }

    private fun generateNewNumbers() {
        upperLimit = upperLimitEditText.text.toString().toIntOrNull() ?: 10

        val intent1 = Intent("com.example.GENERATE_RANDOM_NUMBER")
        intent1.putExtra("upperLimit", upperLimit)
        startActivityForResult(intent1, numberRequest)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val randomNumber = data.getIntExtra("randomNumber", -1)
            when (requestCode) {
                numberRequest -> {
                    number1TextView.text = randomNumber.toString()
                    val intent2 = Intent("com.example.GENERATE_RANDOM_NUMBER")
                    intent2.putExtra("upperLimit", upperLimit)
                    startActivityForResult(intent2, numberRequest2)
                }
                numberRequest2 -> {
                    number2TextView.text = randomNumber.toString()
                }
            }
        }
    }
}