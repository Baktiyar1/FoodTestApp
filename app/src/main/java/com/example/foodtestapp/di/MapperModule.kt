package com.example.foodtestapp.di

import com.example.data.cloud.mappers.category.MapCategoriesFromCloudToData
import com.example.data.cloud.mappers.category.MapCategoryFromCloudToData
import com.example.data.cloud.mappers.product.MapProductFromCloudToData
import com.example.data.cloud.mappers.product.MapProductsFromCloudToData
import com.example.data.cloud.mappers.tag.MapTagFromCloudToData
import com.example.data.cloud.mappers.tag.MapTagsFromCloudToData
import com.example.data.cloud.models.CloudCategory
import com.example.data.cloud.models.CloudProduct
import com.example.data.cloud.models.CloudTag
import com.example.data.mappers.category.MapCategoriesFromDataToDomain
import com.example.data.mappers.category.MapCategoryFromDataToDomain
import com.example.data.mappers.product.MapProductFromDataToDomain
import com.example.data.mappers.product.MapProductFromDomainToData
import com.example.data.mappers.product.MapProductsFromDataToDomain
import com.example.data.mappers.tag.MapTagFromDataToDomain
import com.example.data.mappers.tag.MapTagsFromDataToDomain
import com.example.data.models.DataCategory
import com.example.data.models.DataProduct
import com.example.data.models.DataTag
import com.example.domain.base.Mapper
import com.example.domain.models.DomainCategory
import com.example.domain.models.DomainProduct
import com.example.domain.models.DomainTag
import com.example.foodtestapp.mappers.category.MapCategoriesFromDomainToUi
import com.example.foodtestapp.mappers.category.MapCategoryFromDomainToUi
import com.example.foodtestapp.mappers.product.MapProductFromDomainToUi
import com.example.foodtestapp.mappers.product.MapProductFromUiToDomain
import com.example.foodtestapp.mappers.product.MapProductsFromDomainToUi
import com.example.foodtestapp.mappers.tags.MapTagFromDomainToUi
import com.example.foodtestapp.mappers.tags.MapTagsFromDomainToUi
import com.example.foodtestapp.models.Category
import com.example.foodtestapp.models.Product
import com.example.foodtestapp.models.Tag
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    // PRESENTATION MAPPERS

    @Provides
    @Singleton
    fun providesMapCategoryFromDomainToUi(): Mapper<DomainCategory, Category> =
        MapCategoryFromDomainToUi()

    @Provides
    @Singleton
    fun providesMapCategoriesFromDomainToUi(mapper: Mapper<DomainCategory, Category>): Mapper<List<DomainCategory>, List<Category>> =
        MapCategoriesFromDomainToUi(mapper = mapper)

    @Provides
    @Singleton
    fun providesMapProductFromDomainToUi(): Mapper<DomainProduct, Product> =
        MapProductFromDomainToUi()

    @Provides
    @Singleton
    fun providesMapProductsFromDomainToUi(mapper: Mapper<DomainProduct, Product>): Mapper<List<DomainProduct>, List<Product>> =
        MapProductsFromDomainToUi(mapper = mapper)

    @Provides
    @Singleton
    fun providesMapProductFromUiToDomain(): Mapper<Product, DomainProduct> =
        MapProductFromUiToDomain()

    @Provides
    @Singleton
    fun providesMapTagFromDomainToUi(): Mapper<DomainTag, Tag> = MapTagFromDomainToUi()

    @Provides
    @Singleton
    fun providesMapTagsFromDomainToUi(mapper: Mapper<DomainTag, Tag>): Mapper<List<DomainTag>, List<Tag>> =
        MapTagsFromDomainToUi(mapper = mapper)

    // DATA CLOUD MAPPERS

    @Provides
    @Singleton
    fun providesMapCategoryFromCloudToData(): Mapper<CloudCategory, DataCategory> =
        MapCategoryFromCloudToData()

    @Provides
    @Singleton
    fun providesMapCategoriesFromCloudToData(mapper: Mapper<CloudCategory, DataCategory>): Mapper<List<CloudCategory>, List<DataCategory>> =
        MapCategoriesFromCloudToData(mapper = mapper)

    @Provides
    @Singleton
    fun providesMapProductFromCloudToData(): Mapper<CloudProduct, DataProduct> =
        MapProductFromCloudToData()

    @Provides
    @Singleton
    fun providesMapProductsFromCloudToData(mapper: Mapper<CloudProduct, DataProduct>): Mapper<List<CloudProduct>, List<DataProduct>> =
        MapProductsFromCloudToData(mapper = mapper)

    @Provides
    @Singleton
    fun providesMapTagFromCloudToData(): Mapper<CloudTag, DataTag> = MapTagFromCloudToData()

    @Provides
    @Singleton
    fun providesMapTagsFromCloudToData(mapper: Mapper<CloudTag, DataTag>): Mapper<List<CloudTag>, List<DataTag>> =
        MapTagsFromCloudToData(mapper = mapper)

    // DATA MAPPERS

    @Provides
    @Singleton
    fun providesMapCategoryFromDataToDomain(): Mapper<DataCategory, DomainCategory> =
        MapCategoryFromDataToDomain()

    @Provides
    @Singleton
    fun providesMapCategoriesFromDataToDomain(mapper: Mapper<DataCategory, DomainCategory>): Mapper<List<DataCategory>, List<DomainCategory>> =
        MapCategoriesFromDataToDomain(mapper = mapper)

    @Provides
    @Singleton
    fun providesMapProductFromDataToDomain(): Mapper<DataProduct, DomainProduct> =
        MapProductFromDataToDomain()

    @Provides
    @Singleton
    fun providesMapProductsFromDataToDomain(mapper: Mapper<DataProduct, DomainProduct>): Mapper<List<DataProduct>, List<DomainProduct>> =
        MapProductsFromDataToDomain(mapper = mapper)

    @Provides
    @Singleton
    fun providesMapProductFromDomainToData(): Mapper<DomainProduct, DataProduct> =
        MapProductFromDomainToData()

    @Provides
    @Singleton
    fun providesMapTagFromDataToDomain(): Mapper<DataTag, DomainTag> = MapTagFromDataToDomain()

    @Provides
    @Singleton
    fun providesMapTagsFromDataToDomain(mapper: Mapper<DataTag, DomainTag>): Mapper<List<DataTag>, List<DomainTag>> =
        MapTagsFromDataToDomain(mapper = mapper)

}