package com.lemonade.wordcount.messeges.producer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class UploadUrlProducer(
        @Value("\${messages.topic.upload-url}")
        private val topic: String,
        @Autowired
        private val kafkaTemplate: KafkaTemplate<String, String>
) : Producer(
        topic, kafkaTemplate
)