package com.example.vocabularykt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_words.*
import java.util.*

class WordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)

        val words: ArrayList<Word> = ArrayList<Word>()

        words.add(
            Word(
                "dd",
                "aa"
            )
        )

        words_list.adapter = WordAdapter(this@WordsActivity, words)
    }
}