package com.lemonade.wordcount.service

import com.lemonade.wordcount.model.Word
import com.lemonade.wordcount.repository.WordCountRepository
import org.apache.spark.SparkConf
import org.apache.spark.api.java.JavaRDD
import org.apache.spark.api.java.JavaSparkContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*


@Service
class WordProcessService(
        @Autowired
        val wordCountRepository: WordCountRepository
) {
    val gcsResource = "gs://wordcount-1234/558b6866138d02d3b6e9e5c9bfcadb04cf6c26aafdda1313ebe865faca371f0d"

    init {
        foo()
    }

    fun foo() {
        val conf = SparkConf().setAppName("wordCounts").setMaster("local[*]")
        val sc = JavaSparkContext(conf)
        conf.set("fs.gs.impl", "com.google.cloud.hadoop.fs.gcs.GoogleHadoopFileSystem")
        conf.set("fs.AbstractFileSystem.gs.impl", "com.google.cloud.hadoop.fs.gcs.GoogleHadoopFS")
        conf.set("fs.gs.project.id", "wordcount-297608")
        conf.set("google.cloud.auth.service.account.enable", "true")

        val lines: JavaRDD<String> = sc.textFile(gcsResource)
        val words = lines.flatMap { line: String -> Arrays.asList(*line.split(" ".toRegex()).toTypedArray()).iterator() }

        val wordCounts = words.countByValue()

        for ((key, value) in wordCounts) {
            var word = wordCountRepository.findByIdOrNull(key).let {
                it
            } ?: run {
                Word(key, 0)
            }
            word.count += value
            wordCountRepository.save(word)
        }
    }
}