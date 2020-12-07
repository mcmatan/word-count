package com.lemonade.wordcount.service

import com.lemonade.wordcount.model.Word
import com.lemonade.wordcount.repository.WordCountRepository
import org.apache.spark.SparkConf
import org.apache.spark.api.java.JavaRDD
import org.apache.spark.api.java.JavaSparkContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

interface CountTaskService {
    fun count(resourceName: String)
}

@Service
class CountTaskServiceImp(
        @Autowired
        val wordCountRepository: WordCountRepository,
        val conf: SparkConf? = SparkConf().setAppName("wordCounts").setMaster("local[*]"),
        val sc: JavaSparkContext = JavaSparkContext(conf),
        @Value("\${google.storage.bucket-name}")
        private val bucket: String
): CountTaskService {

    init {
        conf?.set("fs.gs.impl", "com.google.cloud.hadoop.fs.gcs.GoogleHadoopFileSystem")
        conf?.set("fs.AbstractFileSystem.gs.impl", "com.google.cloud.hadoop.fs.gcs.GoogleHadoopFS")
        conf?.set("fs.gs.project.id", "wordcount-297608")
        conf?.set("google.cloud.auth.service.account.enable", "true")
    }

    override fun count(resourceName: String) {
        val resource = "gs://$bucket/$resourceName"
        val lines: JavaRDD<String> = sc.textFile(resource)
        val words = lines.flatMap { line: String -> Arrays.asList(*line.split(" ".toRegex()).toTypedArray()).iterator() }

        val wordCounts = words.countByValue()

        for ((word, value) in wordCounts) {
            var count = wordCountRepository.get(word)
            count += value
            wordCountRepository.set(word, count)
        }

        wordCountRepository.saveToDisk()
    }
}