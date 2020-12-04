package com.lemonade.wordcount.extractor

import com.lemonade.wordcount.cloud.CloudStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PlainTextExtractor(
        @Autowired
        val cloudStorage: CloudStorage
){
    fun extract(text: String) {
        cloudStorage.upload(text)
    }

}