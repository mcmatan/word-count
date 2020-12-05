package com.lemonade.wordcount.repository

import com.lemonade.wordcount.model.Word
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface WordCountRepository : CrudRepository<Word?, String?>