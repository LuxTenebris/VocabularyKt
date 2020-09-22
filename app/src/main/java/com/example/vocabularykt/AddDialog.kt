package com.example.vocabularykt

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.add_dialog.*
import java.util.*

class AddDialog : DialogFragment() {

    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        add_word.setOnClickListener {
            val stEnglish: String = add_english_word.text.toString()
            val stRussian: String = add_translate.text.toString()
            if (stEnglish.isEmpty()) {
                Toast.makeText(context, "You do not enter an english word", Toast.LENGTH_SHORT).show()
            } else {
                if (stRussian.isEmpty()) {
                    Toast.makeText(context, "You do not enter a russian word", Toast.LENGTH_SHORT).show()
                } else {
                    val word = hashMapOf(
                        "english" to stEnglish.substring(0, 1).toUpperCase(Locale.getDefault()) + stEnglish.substring(1).toLowerCase(
                            Locale.getDefault()
                        ),
                        "russian" to stRussian.substring(0,1).toUpperCase(Locale.getDefault()) + stRussian.substring(1).toLowerCase(
                            Locale.getDefault()
                        )
                    )
                    db.collection("words")
                        .add(word)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                    Toast.makeText(context, "successfully added", Toast.LENGTH_SHORT).show()
                    dialog!!.cancel()
                }
            }
        }

        cansel_adding.setOnClickListener {
            dialog!!.cancel()
        }
    }

}

