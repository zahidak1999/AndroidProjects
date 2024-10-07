package com.example.menu

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.menu.ui.theme.MenuTheme

class MainActivity : ComponentActivity() {
    private val firstName = "Zahid Andre"
    private val lastName = "Kristiansen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MenuTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(firstName)
        menu.add(lastName)
        Log.d("Me", "Menu created")
        return true
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MenuTheme {
        Greeting("Android")
    }
}