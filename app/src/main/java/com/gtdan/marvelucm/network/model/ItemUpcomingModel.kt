package com.gtdan.marvelucm.network.model

data class ItemUpcomingModel(
    val id: Int,
    val name: String,
    val type: String,
    val resources: ModelResourcesUpcoming,
    val date: ModelDate
)