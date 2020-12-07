package com.lemonade.wordcount.service

import com.lemonade.wordcount.repository.WordCountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

interface StatisticsService {
    fun wordCount(word: String): Long
}

@Service
class StatisticsServiceImp(
        @Autowired
        val wordCountRepository: WordCountRepository
): StatisticsService  {

    override fun wordCount(word: String): Long = wordCountRepository.get(word)
}