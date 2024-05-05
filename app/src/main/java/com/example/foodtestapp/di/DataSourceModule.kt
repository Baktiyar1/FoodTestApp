package com.example.foodtestapp.di

import com.example.data.cloud.mappers.category.MapCategoriesFromCloudToData
import com.example.data.cloud.mappers.product.MapProductsFromCloudToData
import com.example.data.cloud.mappers.tag.MapTagsFromCloudToData
import com.example.data.cloud.services.FoodService
import com.example.data.cloud.source.CloudDataSource
import com.example.data.cloud.source.CloudDataSourceImpl
import com.example.foodtestapp.ui.product.ProductCountRepository
import com.example.foodtestapp.ui.product.ProductCountRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesProductCountRepository(): ProductCountRepository = ProductCountRepositoryImpl()

    @Provides
    @Singleton
    fun providesCloudDataSource(
        foodService: FoodService,
        mapCategoriesFromCloudToData: MapCategoriesFromCloudToData,
        mapProductsFromCloudToData: MapProductsFromCloudToData,
        mapTagsFromCloudToData: MapTagsFromCloudToData,
    ): CloudDataSource = CloudDataSourceImpl(
        foodService = foodService,
        mapCategoriesFromCloudToData = mapCategoriesFromCloudToData,
        mapProductsFromCloudToData = mapProductsFromCloudToData,
        mapTagsFromCloudToData = mapTagsFromCloudToData
    )

}