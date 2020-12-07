package com.lemonade.wordcount.extractor

import com.google.cloud.storage.BlobId
import com.lemonade.wordcount.cloud.CloudStorage
import com.lemonade.wordcount.messeges.producer.CountProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.FileInputStream
import java.lang.RuntimeException
import java.net.URL

@Component
class Extractor(
        @Autowired
        val cloudStorage: CloudStorage,
        @Autowired
        val countProducer: CountProducer
) {

    fun extractPath(path: String) =
            produce(cloudStorage.upload(FileInputStream(path)))

    fun extractPlainText(plainText: String) =
            produce(cloudStorage.upload(plainText))

    fun extractUrl(url: String) =
            produce(cloudStorage.upload(URL(url).openStream()))

    private fun produce(blobId: BlobId?) =
            blobId?.let {
                countProducer.send(blobId.name)
            } ?: run {
                throw RuntimeException("Unable to extract = " + blobId?.name)
            }
}