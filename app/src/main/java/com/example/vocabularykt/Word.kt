package com.example.vocabularykt

class Word (var english : String, var russian : String, var id : String):Comparable<Word> {
    override fun compareTo(other: Word): Int {
        return english.compareTo(other.english)
    }
}