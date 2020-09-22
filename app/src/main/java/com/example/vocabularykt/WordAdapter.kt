package com.example.vocabularykt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class WordAdapter(context: Context, private var data: List<*>) :
    ArrayAdapter<Any?>(context, R.layout.word) {

    override fun getCount(): Int {
        // возвращаем количество элементов списка
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val word = data[position] as Word
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.word, parent, false)
        (view.findViewById<View>(R.id.english_word) as TextView).text = word.english
        (view.findViewById<View>(R.id.russian_word) as TextView).text = word.russian
        return view
    }

}