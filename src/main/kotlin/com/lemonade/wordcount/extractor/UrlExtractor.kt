package com.lemonade.wordcount.extractor

import com.lemonade.wordcount.cloud.CloudStorage
import com.lemonade.wordcount.messeges.producer.CountProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.net.URL


@Component
class UrlExtractor(
        @Autowired
        val cloudStorage: CloudStorage,
        @Autowired
        val countProducer: CountProducer
) {
    fun extract(url: String) {
        val input = URL(url).openStream()
        val res = cloudStorage.upload(input)
        res?.let {
            countProducer.send(res.name)
        } ?: run {
            throw RuntimeException("Unable to extract path = " + url)
        }
    }
}