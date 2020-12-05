package com.lemonade.wordcount.extractor

import com.lemonade.wordcount.cloud.CloudStorage
import com.lemonade.wordcount.messeges.producer.CountProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.FileInputStream
import java.lang.RuntimeException

@Component
class PathExtractor(
        @Autowired
        val cloudStorage: CloudStorage,
        @Autowired
        val countProducer: CountProducer
) {

    fun extract(path: String) {
        val file = FileInputStream(path)
        val res = cloudStorage.upload(file)
        res?.let {
            countProducer.send(res.name)
        } ?: run {
            throw RuntimeException("Unable to extract path = " + path)
        }
    }
}