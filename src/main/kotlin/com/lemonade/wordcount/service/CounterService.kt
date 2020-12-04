package com.lemonade.wordcount.service

import com.lemonade.wordcount.messeges.producer.CountPathProducer
import com.lemonade.wordcount.messeges.producer.CountPlainTextProducer
import com.lemonade.wordcount.messeges.producer.CountUrlProducer
import com.lemonade.wordcount.model.CounterUpdate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface CounterService {
    fun count(counterUpdate: CounterUpdate)
}

@Service
class CounterServiceImp(
        @Autowired
        val countPlainTextProducer: CountPlainTextProducer,
        @Autowired
        val countUrlProducer: CountUrlProducer,
        @Autowired
        val countPathProducer: CountPathProducer
): CounterService {
    override fun count(counterUpdate: CounterUpdate) {
        counterUpdate.text?.let {
            countPlainTextProducer.send(it)
        }
        counterUpdate.url?.let {
            countUrlProducer.send(it)
        }
        counterUpdate.path?.let {
            countPathProducer.send(it)
        }
    }
}