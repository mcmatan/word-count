package com.lemonade.wordcount.extractor

import com.lemonade.wordcount.cloud.CloudStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.FileInputStream

@Component
class PathExtractor(
        @Autowired
        val cloudStorage: CloudStorage
) {

    fun extract(path: String) {
        val file = FileInputStream(path)
        cloudStorage.upload(file)
    }
}