package com.lemonade.wordcount.messeges.consumer

import com.lemonade.wordcount.extractor.UrlExtractor
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class UploadUrlConsumer(
        @Autowired
        val extractor: UrlExtractor
)  {
    @KafkaListener(topics = ["\${messages.topic.upload-url}"])
    fun receive(consumerRecord: ConsumerRecord<*, *>) {
        extractor.extract(consumerRecord.value().toString())
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(UploadPlainTextConsumer::class.java)
    }
}