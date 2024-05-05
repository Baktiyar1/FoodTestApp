package com.example.domain.models

data class DomainProduct(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val priceCurrent: Double,
    val priceOld: Double?,
    val categoryId: Int,
    val measure: Int,
    val measureUnit: String,
    val energy: Double,
    val proteins: Double,
    val fats: Double,
    val carbohydrates: Double,
    val tagIds: List<Int>,
)