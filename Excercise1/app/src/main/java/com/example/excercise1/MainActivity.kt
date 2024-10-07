package com.example.excercise1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    private val firstName = "Zahid Andre"
    private val lastName = "Kristiansen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("INFT2501", "Activity created")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.add(firstName)
        menu.add(lastName)
        Log.d("INFT2501", "Menu created") // Displayed in Logcat
        return true // Ensures the menu is displayed
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title == firstName) {
            Log.w("MenuSelection", "First name selected: $firstName")
        } else if (item.title == lastName) {
            Log.e("MenuSelection", "Last name selected: $lastName")
        }
        return true
    }
}
