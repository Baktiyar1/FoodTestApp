package com.example.data.cloud.models

import com.google.gson.annotations.SerializedName

data class CloudProduct(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("price_current") val priceCurrent: Double,
    @SerializedName("price_old") val priceOld: Double?,
    @SerializedName("category_id") val categoryId: Int,
    @SerializedName("measure") val measure: Int,
    @SerializedName("measure_unit") val measureUnit: String,
    @SerializedName("energy_per_100_grams") val energy: Double,
    @SerializedName("proteins_per_100_grams") val proteins: Double,
    @SerializedName("fats_per_100_grams") val fats: Double,
    @SerializedName("carbohydrates_per_100_grams") val carbohydrates: Double,
    @SerializedName("tag_ids") val tagIds: List<Int>,
)