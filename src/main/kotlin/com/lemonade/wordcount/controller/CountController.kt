package com.lemonade.wordcount.controller

import com.lemonade.wordcount.model.CounterUpdateDTO
import com.lemonade.wordcount.model.toEntity
import com.lemonade.wordcount.service.AddCountTaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("count")
class CountController(
        @Autowired
        val addCountTaskService: AddCountTaskService
) {

    @PostMapping
    fun count(@RequestBody counterUpdateDTO: CounterUpdateDTO) =
            addCountTaskService.count(counterUpdateDTO.toEntity())

}