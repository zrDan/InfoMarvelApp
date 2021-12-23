package com.gtdan.marvelucm.network.model

import com.squareup.moshi.Json

data class ItemResponseModel(@Json(name = "data") val items: List<ItemModel>)