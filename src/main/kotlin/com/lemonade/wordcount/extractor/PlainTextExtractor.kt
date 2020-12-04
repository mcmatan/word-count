package com.lemonade.wordcount.extractor

import com.google.cloud.storage.StorageOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets.UTF_8


@Component
class PlainTextExtractor(
        @Value("google.storage.bucket")
        var bucketName: String
){
    val storage = StorageOptions.getDefaultInstance().service
    val bucket = storage.get(bucketName)

    fun extract(text: String) {
        val value = "Hello, World!"
        val bytes: ByteArray = value.toByteArray(UTF_8)
        val blob = bucket.create(Math.random().toString(), bytes)
    }

}