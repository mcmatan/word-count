package com.lemonade.wordcount.controller

import com.lemonade.wordcount.model.CounterUpdateDTO
import com.lemonade.wordcount.model.toEntity
import com.lemonade.wordcount.service.CounterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("counter")
class CounterController(
        @Autowired
        val counterService: CounterService
) {

    @PostMapping
    fun update(@RequestBody counterUpdateDTO: CounterUpdateDTO) =
            counterService.count(counterUpdateDTO.toEntity())

}