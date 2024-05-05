package com.example.data.cloud.source

import android.util.Log
import com.example.data.cloud.models.CloudCategory
import com.example.data.cloud.models.CloudProduct
import com.example.data.cloud.models.CloudTag
import com.example.data.cloud.services.FoodService
import com.example.data.models.DataCategory
import com.example.data.models.DataProduct
import com.example.data.models.DataTag
import com.example.domain.base.Mapper
import com.example.domain.utils.APPLICATION_EXCEPTION

class CloudDataSourceImpl(
    private val foodService: FoodService,
    private val mapCategoriesFromCloudToData: Mapper<List<CloudCategory>, List<DataCategory>>,
    private val mapProductsFromCloudToData: Mapper<List<CloudProduct>, List<DataProduct>>,
    private val mapTagsFromCloudToData: Mapper<List<CloudTag>, List<DataTag>>,
) : CloudDataSource {
    override suspend fun getAllCategories(): List<DataCategory> = try {
        val categoryResponse = foodService.getAllCategories()
        if (categoryResponse.isSuccessful) mapCategoriesFromCloudToData.map(
            from = categoryResponse.body().orEmpty()
        ) else emptyList()
    } catch (e: Throwable) {
        Log.d(
            APPLICATION_EXCEPTION,
            "CloudDataSourceImpl getAllCategories: ${e.message ?: e.stackTraceToString()}"
        )
        emptyList()
    }

    override suspend fun getAllProducts(): List<DataProduct> = try {
        val productResponse = foodService.getAllProducts()
        if (productResponse.isSuccessful) mapProductsFromCloudToData.map(
            from = productResponse.body().orEmpty()
        ) else emptyList()
    } catch (e: Throwable) {
        Log.d(
            APPLICATION_EXCEPTION,
            "CloudDataSourceImpl getAllProducts: ${e.message ?: e.stackTraceToString()}"
        )
        emptyList()
    }

    override suspend fun getAllTags(): List<DataTag> = try {
        val tagResponse = foodService.getAllTags()
        if (tagResponse.isSuccessful) mapTagsFromCloudToData.map(
            from = tagResponse.body().orEmpty()
        ) else emptyList()
    } catch (e: Throwable) {
        Log.d(
            APPLICATION_EXCEPTION,
            "CloudDataSourceImpl getAllTags: ${e.message ?: e.stackTraceToString()}"
        )
        emptyList()
    }
}