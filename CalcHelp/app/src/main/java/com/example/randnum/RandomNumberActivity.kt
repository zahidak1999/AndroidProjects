package com.example.randnum

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class RandomNumberActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val upperLimit = intent.getIntExtra("upperLimit", 100)
        val value = (0..upperLimit).random()

        // Toast.makeText(this, "Random number: $value", Toast.LENGTH_SHORT).show()

        val resultIntent = Intent()
        resultIntent.putExtra("randomNumber", value)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}