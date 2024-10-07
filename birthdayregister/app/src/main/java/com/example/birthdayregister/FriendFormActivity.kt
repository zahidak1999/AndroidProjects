package com.example.birthdayregister

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class FriendFormActivity : Activity() {

    private lateinit var nameEditText: EditText
    private lateinit var birthdateEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    private var friendPosition: Int = -1 // New blood first

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_form)

        nameEditText = findViewById(R.id.nameEditText)
        birthdateEditText = findViewById(R.id.birthdateEditText)
        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)

        val extras = intent.extras
        if (extras != null) {
            friendPosition = extras.getInt("position", -1)
            val name = extras.getString("name", "")
            val birthdate = extras.getString("birthdate", "")
            nameEditText.setText(name)
            birthdateEditText.setText(birthdate)
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val birthdate = birthdateEditText.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("name", name)
            resultIntent.putExtra("birthdate", birthdate)
            resultIntent.putExtra("position", friendPosition)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
