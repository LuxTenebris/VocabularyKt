package com.example.vocabularykt

class Word (private var english : String, private var russian : String):Comparable<Word> {
    override fun compareTo(other: Word): Int {
        return english.compareTo(other.english)
    }

    fun getEnglish() : String{
        return english
    }

    fun getRussian() : String{
        return russian
    }

}