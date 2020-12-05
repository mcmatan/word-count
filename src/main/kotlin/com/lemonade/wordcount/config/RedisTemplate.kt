package com.lemonade.wordcount.config

import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate


@Bean
fun jedisConnectionFactory(): JedisConnectionFactory? {
    return JedisConnectionFactory()
}

@Bean
fun redisTemplate(): RedisTemplate<String, Any>? {
    val template = RedisTemplate<String, Any>()
    template.setConnectionFactory(jedisConnectionFactory()!!)
    return template
}