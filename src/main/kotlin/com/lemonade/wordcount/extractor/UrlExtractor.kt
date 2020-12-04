package com.lemonade.wordcount.extractor

import com.lemonade.wordcount.cloud.CloudStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.net.URL


@Component
class UrlExtractor(
        @Autowired
        val cloudStorage: CloudStorage
) {
    fun extract(url: String) {
        val input = URL(url).openStream()
        cloudStorage.upload(input)
    }
}