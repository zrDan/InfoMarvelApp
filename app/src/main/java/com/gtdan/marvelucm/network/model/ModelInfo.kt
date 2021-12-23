package com.gtdan.marvelucm.network.model

data class ModelInfo(
    val score: String,
    val director: String,
    val time: String,
    val date: ModelDate,
    val resume: String,
    val seasons: String? = null,
    val chapters: String? = null
)