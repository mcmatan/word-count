package com.lemonade.wordcount.messeges.consumer

import com.lemonade.wordcount.service.CountTaskService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CountConsumer(
        @Autowired
        val countService: CountTaskService
) {
    @KafkaListener(topics = ["\${messages.topic.count}"])
    fun receive(consumerRecord: ConsumerRecord<*, *>) {
        countService.count(consumerRecord.value().toString())
    }
}