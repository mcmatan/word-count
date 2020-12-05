package com.lemonade.wordcount.extractor

import com.lemonade.wordcount.cloud.CloudStorage
import com.lemonade.wordcount.messeges.producer.CountProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class PlainTextExtractor(
        @Autowired
        val cloudStorage: CloudStorage,
        @Autowired
        val countProducer: CountProducer
){
    fun extract(text: String) {
        val res = cloudStorage.upload(text)
        res?.let {
            countProducer.send(res.name)
        } ?: run {
            throw RuntimeException("Unable to extract path = " + text)
        }
    }

}