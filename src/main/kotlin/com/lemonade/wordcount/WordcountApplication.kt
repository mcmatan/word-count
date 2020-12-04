package com.lemonade.wordcount

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WordcountApplication

fun main(args: Array<String>) {
	runApplication<WordcountApplication>(*args)
}
