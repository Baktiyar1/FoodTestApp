package com.example.data.cloud.source

import com.example.data.models.DataCategory
import com.example.data.models.DataProduct
import com.example.data.models.DataTag

interface CloudDataSource {
    suspend fun getAllCategories(): List<DataCategory>
    suspend fun getAllProducts(): List<DataProduct>
    suspend fun getAllTags(): List<DataTag>
}