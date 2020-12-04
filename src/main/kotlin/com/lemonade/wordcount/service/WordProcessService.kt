package com.lemonade.wordcount.service

import org.springframework.stereotype.Service

//import org.apache.spark.SparkConf
//import org.apache.spark.api.java.JavaSparkContext
//import org.springframework.core.io.WritableResource
//import org.springframework.stereotype.Service
//import scala.Tuple2
//import java.util.*


@Service
class WordProcessService {
    val gcsResource = "gs://wordcount-1234/558b6866138d02d3b6e9e5c9bfcadb04cf6c26aafdda1313ebe865faca371f0d"

//    init {
//        foo()
//    }

//    fun foo() {
//        (gcsResource as WritableResource).outputStream.use { os -> os.write("foo".toByteArray()) }
//
//        val conf = SparkConf().setAppName("lemonade.sparkExample.WordCount").setMaster("local")
//        val context = JavaSparkContext(conf)
//
//        context.textFile(gcsResource).flatMap { elem -> Arrays.asList(elem.split(" ")) }
//                .mapToPair { elem -> Tuple2(elem, 1) }  // Create Tuple of form (word, 1)
//                .reduceByKey { a, b -> a!! + b!! }      // Sum up identical words (word, n)
//                .mapToPair { it.swap() }                // Swap tuple (n, word)
//                .sortByKey()                            // Sort by n (default = ascending)
//                .saveAsTextFile("temp/out")               // Save output in folder "output"
//
//    }
}