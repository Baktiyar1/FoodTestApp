package com.example.data.cloud.services

import com.example.data.cloud.models.CloudCategory
import com.example.data.cloud.models.CloudProduct
import com.example.data.cloud.models.CloudTag
import retrofit2.Response
import retrofit2.http.GET

interface FoodService {

    @GET(CATEGORIES_END_POINT)
    suspend fun getAllCategories(): Response<List<CloudCategory>>

    @GET(PRODUCTS_END_POINT)
    suspend fun getAllProducts(): Response<List<CloudProduct>>

    @GET(TAGS_END_POINT)
    suspend fun getAllTags(): Response<List<CloudTag>>

    companion object {
        private const val CATEGORIES_END_POINT = "Categories.json"
        private const val PRODUCTS_END_POINT = "Products.json"
        private const val TAGS_END_POINT = "Tags.json"
    }
}