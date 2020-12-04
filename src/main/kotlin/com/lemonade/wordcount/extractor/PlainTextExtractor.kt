package com.lemonade.wordcount.extractor

import com.google.cloud.storage.StorageOptions
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets.UTF_8


@Component
class PlainTextExtractor(
        @Value("\${google.storage.bucket-name}")
        private val bucketName: String
){
    val storage = StorageOptions.getDefaultInstance().service
    val bucket = storage.get(bucketName)

    fun extract(text: String) {
        val value = "Hello, World!"
        val bytes: ByteArray = value.toByteArray(UTF_8)
        val sha256hex: String = DigestUtils.sha256Hex(Math.random().toString())
        val blob = bucket.create(sha256hex, bytes)
    }

}