package com.lemonade.wordcount.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileWriter
import java.io.IOException

@Component
class WordCountRepository(
        var map: MutableMap<String, Long>,
        val path: String = "./out/word-count.json",
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()
) {

    init {
        val jsonString = File(this.path).readText(Charsets.UTF_8)
        map = gson.fromJson(jsonString, object : TypeToken<Map<String, Any>>() {}.type)
    }

    fun set(word: String, value: Long) = map.set(word, value)
    fun get(word: String) = map.getOrDefault(word, 0)

    fun saveToDisk() =
            gson.toJson(map).let { jsonString ->
                try {
                    val writer = FileWriter(File(this.path))
                    writer.use { it.write(jsonString) }
                } catch (e: IOException) {
                    print("error parsing map from file ${e.localizedMessage}")
                }
            }
}