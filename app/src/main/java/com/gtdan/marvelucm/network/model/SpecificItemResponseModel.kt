package com.gtdan.marvelucm.network.model

import com.squareup.moshi.Json

data class SpecificItemResponseModel(@Json(name = "data") val item: ItemModel)
