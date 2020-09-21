package com.example.vocabularykt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        open_words.setOnClickListener {
            startActivity(Intent(this, WordsActivity :: class.java))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_word -> AddDialog().show(supportFragmentManager, "addDialog")
            R.id.add_menu_settings -> startActivity(Intent(this, SettingsActivity :: class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}