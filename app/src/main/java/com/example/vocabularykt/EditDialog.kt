package com.example.vocabularykt

import android.content.ContentValues
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.add_dialog.*
import java.util.*

class EditDialog : DialogFragment() {

    private val db = Firebase.firestore

    lateinit var english : String
    lateinit var russian : String
    lateinit var id : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        english = arguments?.get("english") as String
        russian = arguments!!.get("russian") as String
        id = arguments!!.get("id") as String

        return inflater.inflate(R.layout.add_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()

        add_english_word.text = Editable.Factory.getInstance().newEditable(english)
        add_translate.text = Editable.Factory.getInstance().newEditable(russian)

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
                            Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                            db.collection("words").document(id)
                                .delete()
                                .addOnSuccessListener {
                                    Log.d("DELETE", "DocumentSnapshot successfully deleted!")

                                }
                                .addOnFailureListener { e -> Log.w("DELETE", "Error deleting document", e) }
                        }
                        .addOnFailureListener { e ->
                            Log.w(ContentValues.TAG, "Error adding document", e)
                        }
                    Toast.makeText(context, "successfully edited", Toast.LENGTH_SHORT).show()
                    dialog!!.dismiss()
                }
            }
        }

        cansel_adding.setOnClickListener {
            dialog!!.dismiss()
        }
    }


}