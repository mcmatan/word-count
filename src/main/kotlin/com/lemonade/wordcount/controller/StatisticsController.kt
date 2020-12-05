package com.lemonade.wordcount.controller

import com.lemonade.wordcount.service.StatisticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("statistics")
class StatisticsController(
        @Autowired
        val statisticsService: StatisticsService
) {

    @GetMapping
    fun wordCount(@RequestParam word: String) = statisticsService.wordCount(word)
}