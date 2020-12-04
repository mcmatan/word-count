package com.lemonade.wordcount.service

import com.lemonade.wordcount.messeges.producer.CountPlainTextProducer
import com.lemonade.wordcount.model.CounterUpdate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface CounterService {
    fun count(counterUpdate: CounterUpdate)
}

@Service
class CounterServiceImp(
        @Autowired
        val countPlainTextProducer: CountPlainTextProducer
): CounterService {
    override fun count(counterUpdate: CounterUpdate) {
        counterUpdate.text?.let {
            countPlainTextProducer.send(it)
        }
    }
}