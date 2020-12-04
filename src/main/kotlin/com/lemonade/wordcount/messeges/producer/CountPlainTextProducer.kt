package com.lemonade.wordcount.messeges.producer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CountPlainTextProducer(
        @Value("messages.topic.count-plain-text")
        private val topic: String,
        @Autowired
        private val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun send(payload: String) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topic)
        kafkaTemplate.send("count-plain-text", payload)
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(CountPlainTextProducer::class.java)
    }
}