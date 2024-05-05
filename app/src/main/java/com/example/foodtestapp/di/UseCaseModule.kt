package com.example.foodtestapp.di

import com.example.domain.repositories.FoodRepository
import com.example.domain.usecases.category.GetAllCategoriesUseCase
import com.example.domain.usecases.category.GetAllCategoriesUseCaseImpl
import com.example.domain.usecases.product.GetAllProductsUseCase
import com.example.domain.usecases.product.GetAllProductsUseCaseImpl
import com.example.domain.usecases.tag.GetAllTagsUseCase
import com.example.domain.usecases.tag.GetAllTagsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesGetAllCategoryUseCase(foodRepository: FoodRepository): GetAllCategoriesUseCase =
        GetAllCategoriesUseCaseImpl(foodRepository)

    @Provides
    @Singleton
    fun providesGetAllProductUseCase(foodRepository: FoodRepository): GetAllProductsUseCase =
        GetAllProductsUseCaseImpl(foodRepository)

    @Provides
    @Singleton
    fun providesGetAllTagsUseCase(foodRepository: FoodRepository): GetAllTagsUseCase =
        GetAllTagsUseCaseImpl(foodRepository)

}