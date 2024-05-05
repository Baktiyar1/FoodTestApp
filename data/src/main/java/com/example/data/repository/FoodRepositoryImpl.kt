package com.example.data.repository

import android.util.Log
import com.example.data.cloud.source.CloudDataSource
import com.example.data.models.DataCategory
import com.example.data.models.DataProduct
import com.example.data.models.DataTag
import com.example.domain.base.Mapper
import com.example.domain.base.Result
import com.example.domain.models.DomainCategory
import com.example.domain.models.DomainProduct
import com.example.domain.models.DomainTag
import com.example.domain.repositories.FoodRepository
import com.example.domain.utils.APPLICATION_EXCEPTION
import java.util.concurrent.CancellationException
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val mapCategoriesFromDataToDomain: Mapper<List<DataCategory>, List<DomainCategory>>,
    private val mapProductsFromDataToDomain: Mapper<List<DataProduct>, List<DomainProduct>>,
    private val mapTagsFromDataToDomain: Mapper<List<DataTag>, List<DomainTag>>,
) : FoodRepository {

    override suspend fun getAllCategories(): Result<List<DomainCategory>> = try {
        Result.Success(data = mapCategoriesFromDataToDomain.map(cloudDataSource.getAllCategories()))
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Log.d(APPLICATION_EXCEPTION, "RepositoryImpl getAllCategories ${e.stackTraceToString()}")
        Result.Error(message = e.message ?: e.stackTraceToString())
    }

    override suspend fun getAllProducts(): Result<List<DomainProduct>> = try {
        Result.Success(data = mapProductsFromDataToDomain.map(cloudDataSource.getAllProducts()))
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Log.d(APPLICATION_EXCEPTION, "RepositoryImpl getAllProducts ${e.stackTraceToString()}")
        Result.Error(message = e.message ?: e.stackTraceToString())
    }

    override suspend fun getAllTags(): Result<List<DomainTag>> = try {
        Result.Success(data = mapTagsFromDataToDomain.map(cloudDataSource.getAllTags()))
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Log.d(APPLICATION_EXCEPTION, "RepositoryImpl getAllTags ${e.stackTraceToString()}")
        Result.Error(message = e.message ?: e.stackTraceToString())
    }
}