package com.example.vocabularykt

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_words.*
import java.util.*


class WordsActivity : AppCompatActivity() {

    private val words: ArrayList<Word> = ArrayList<Word>()
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)

        registerForContextMenu(words_list)

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

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        var inflater : MenuInflater = menuInflater
        return inflater.inflate(R.menu.word_list_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterContextMenuInfo

        when(item.itemId){
            R.id.delete_word -> db.collection("words").document(words[info.id.toInt()].id)
                .delete()
                .addOnSuccessListener {
                    Log.d("DELETE", "DocumentSnapshot successfully deleted!")
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
                .addOnFailureListener { e -> Log.w("DELETE", "Error deleting document", e) }

            R.id.edit_word -> Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show()

            R.id.add_to_favorite -> Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show()
        }
        return super.onContextItemSelected(item)
    }
}