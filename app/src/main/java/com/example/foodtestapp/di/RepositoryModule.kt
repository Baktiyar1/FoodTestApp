package com.example.foodtestapp.di

import com.example.data.cloud.source.CloudDataSource
import com.example.data.mappers.category.MapCategoriesFromDataToDomain
import com.example.data.mappers.product.MapProductsFromDataToDomain
import com.example.data.mappers.tag.MapTagsFromDataToDomain
import com.example.data.repository.FoodRepositoryImpl
import com.example.domain.repositories.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        cloudDataSource: CloudDataSource,
        mapCategoriesFromDataToDomain: MapCategoriesFromDataToDomain,
        mapProductsFromDataToDomain: MapProductsFromDataToDomain,
        mapTagsFromDataToDomain: MapTagsFromDataToDomain,
    ): FoodRepository = FoodRepositoryImpl(
        cloudDataSource = cloudDataSource,
        mapCategoriesFromDataToDomain = mapCategoriesFromDataToDomain,
        mapProductsFromDataToDomain = mapProductsFromDataToDomain,
        mapTagsFromDataToDomain = mapTagsFromDataToDomain,
    )
}