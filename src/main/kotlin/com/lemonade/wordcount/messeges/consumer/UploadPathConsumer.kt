package com.lemonade.wordcount.messeges.consumer

import com.lemonade.wordcount.extractor.Extractor
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class UploadPathConsumer(
        @Autowired
        val extractor: Extractor
) {
    @KafkaListener(topics = ["\${messages.topic.upload-path}"])
    fun receive(consumerRecord: ConsumerRecord<*, *>) {
        extractor.extractPath(consumerRecord.value().toString())
    }
}