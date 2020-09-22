package com.example.vocabularykt

import android.R.attr.name
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_words.*
import java.util.*


class WordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)

        val db = Firebase.firestore

        val words: ArrayList<Word> = ArrayList<Word>()

        db.collection("words")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("get_word", "${document.id} => ${document.data}")
                    words.add(Word(
                        document.get("english") as String,
                        document.get("russian") as String,
                        document.id
                    ))
                }

                words.sort()
                
                words_list.adapter = WordAdapter(this@WordsActivity, words)

            }
            .addOnFailureListener { exception ->
                Log.w("get_word", "Error getting documents.", exception)
            }

        val itemListener =
            OnItemClickListener { parent, v, position, id -> // получаем выбранный пункт

            }
        words_list.onItemClickListener = itemListener

    }
}