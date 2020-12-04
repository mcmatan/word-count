package com.lemonade.wordcount.cloud

import com.google.cloud.storage.BlobId
import com.google.cloud.storage.Bucket
import com.google.cloud.storage.StorageOptions
import org.springframework.stereotype.Component
import java.io.InputStream
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.beans.factory.annotation.Value
import java.nio.charset.StandardCharsets

@Component
class CloudStorage(
        @Value("\${google.storage.bucket-name}")
        private val bucketName: String
) {
    val storage = StorageOptions.getDefaultInstance().service
    final var bucket: Bucket? = null

    init {
        bucket = storage.get(bucketName)
    }

    fun upload(text: String): BlobId? {
        val bytes: ByteArray = text.toByteArray(StandardCharsets.UTF_8)
        val sha256hex: String = DigestUtils.sha256Hex(Math.random().toString())
        val blob = bucket?.create(sha256hex, bytes)
        return blob?.blobId
    }

    fun upload(inputStream: InputStream): BlobId? {
        val sha256hex: String = DigestUtils.sha256Hex(Math.random().toString())
        val blob = bucket?.create(sha256hex, inputStream)
        return blob?.blobId
    }
}