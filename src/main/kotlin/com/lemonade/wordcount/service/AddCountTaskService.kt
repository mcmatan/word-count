package com.lemonade.wordcount.service

import com.lemonade.wordcount.messeges.producer.UploadPathProducer
import com.lemonade.wordcount.messeges.producer.UploadPlainTextProducer
import com.lemonade.wordcount.messeges.producer.UploadUrlProducer
import com.lemonade.wordcount.model.CounterUpdate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface AddCountTaskService {
    fun count(counterUpdate: CounterUpdate)
}

@Service
class AddCountTaskServiceImp(
        @Autowired
        val uploadPlainTextProducer: UploadPlainTextProducer,
        @Autowired
        val uploadUrlProducer: UploadUrlProducer,
        @Autowired
        val uploadPathProducer: UploadPathProducer
): AddCountTaskService {
    override fun count(counterUpdate: CounterUpdate) {
        counterUpdate.text?.let {
            uploadPlainTextProducer.send(it)
        }
        counterUpdate.url?.let {
            uploadUrlProducer.send(it)
        }
        counterUpdate.path?.let {
            uploadPathProducer.send(it)
        }
    }
}