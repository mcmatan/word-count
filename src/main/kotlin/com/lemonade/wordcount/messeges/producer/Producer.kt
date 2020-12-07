package com.lemonade.wordcount.messeges.producer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate

abstract class Producer(
        private val topic: String,
        private val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun send(payload: String) {
        LOGGER.debug("PRODUCED TOPIC $topic payload: $payload")
        kafkaTemplate.send(topic, payload)
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(UploadPathProducer::class.java)
    }
}