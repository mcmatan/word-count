package com.lemonade.wordcount.messeges.consumer

import com.lemonade.wordcount.extractor.PlainTextExtractor
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component


@Component
class UploadPlainTextConsumer(
        @Autowired
        val extractor: PlainTextExtractor
)  {
    @KafkaListener(topics = ["\${messages.topic.upload-plain-text}"])
    fun receive(consumerRecord: ConsumerRecord<*, *>) {
        extractor.extract(consumerRecord.value().toString())
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(UploadPlainTextConsumer::class.java)
    }
}