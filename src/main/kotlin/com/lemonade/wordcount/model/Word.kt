package com.lemonade.wordcount.model

import org.springframework.data.redis.core.RedisHash
import java.io.Serializable


@RedisHash("Word")
class Word(
        var id: String? = null,
        var count: Long = 0
) : Serializable