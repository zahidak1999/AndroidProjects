package com.example.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ListFragment.OnItemSelectedListener {

    private val movies = listOf(
        Movie("Batman Begins", "Batman saves Gotham."),
        Movie("The Prestige", "A confusing movie where a simple rewatch could make everyone understand it."),
        Movie("The Dark Knight", "Batman saves Gotham once again."),
        Movie("Inception", "A confusing movie that im pretty sure almost nobody understood, and im still annoyed that someone acted all arrogant saying they understood, but couldn't give a proper explanation.")
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            updateDetailFragment(currentIndex)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_previous -> {
                if (currentIndex > 0) {
                    currentIndex--
                    updateDetailFragment(currentIndex)
                }
                true
            }
            R.id.menu_next -> {
                if (currentIndex < movies.size - 1) {
                    currentIndex++
                    updateDetailFragment(currentIndex)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemSelected(position: Int) {
        currentIndex = position
        updateDetailFragment(position)
    }

    private fun updateDetailFragment(position: Int) {
        val detailFragment = supportFragmentManager.findFragmentById(R.id.fragmentDetail) as? DetailFragment
        detailFragment?.updateContent(movies[position])
    }
}
