package com.gtdan.marvelucm.network.model

import com.squareup.moshi.Json

data class ItemUpcominResponseModel(@Json(name = "data") val items: List<ItemUpcomingModel>)