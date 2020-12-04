package com.lemonade.wordcount.messeges.consumer

import com.lemonade.wordcount.extractor.PlainTextExtractor
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch


@Component
class CountPlainTextConsumer(
        @Autowired
        val extractor: PlainTextExtractor
)  {

    @KafkaListener(topics = ["\${messages.topic.count-plain-text}"])
    fun receive(consumerRecord: ConsumerRecord<*, *>) {
        LOGGER.info("received payload='{}'", consumerRecord.toString())
        extractor.extract(consumerRecord.toString())
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(CountPlainTextConsumer::class.java)
    }
}