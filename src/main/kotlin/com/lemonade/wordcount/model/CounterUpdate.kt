package com.lemonade.wordcount.model

data class CounterUpdateDTO(
        val text: String? = null,
        val url: String? = null,
        val path: String? = null
)

class CounterUpdate(
        val text: String? = null,
        val url: String? = null,
        val path: String? = null
)

fun CounterUpdateDTO.toEntity() =
        CounterUpdate(text, url, path)