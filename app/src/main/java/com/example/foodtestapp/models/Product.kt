package com.example.foodtestapp.models

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.immutableListOf

@Immutable
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val priceCurrent: Double,
    val priceOld: Double,
    val categoryId: Int,
    val measure: Int,
    val measureUnit: String,
    val energy: Double,
    val proteins: Double,
    val fats: Double,
    val carbohydrates: Double,
    val tagIds: ImmutableList<Int>,
    val filterTags: ImmutableList<FilterTag>,
) {
    companion object {
        val unknown = Product(
            id = -1,
            name = "QQ",
            description = "ajsckjs",
            image = "1.jpg",
            priceCurrent = 400.0,
            priceOld = 450.0,
            categoryId = 1,
            measure = 180,
            measureUnit = "г",
            energy = 55.5,
            proteins = 66.6,
            fats = 44.4,
            carbohydrates = 77.7,
            tagIds = immutableListOf(),
            filterTags = immutableListOf()
        )
    }
}