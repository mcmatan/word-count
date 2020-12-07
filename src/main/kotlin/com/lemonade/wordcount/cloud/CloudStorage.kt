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

    fun upload(text: String): BlobId? =
            text.toByteArray(StandardCharsets.UTF_8).let {
                bucket?.create(_sha256(), it)?.blobId
            }

    fun upload(inputStream: InputStream): BlobId? =
            bucket?.create(_sha256(), inputStream)?.blobId

    private fun _sha256() = DigestUtils.sha256Hex(Math.random().toString())
}