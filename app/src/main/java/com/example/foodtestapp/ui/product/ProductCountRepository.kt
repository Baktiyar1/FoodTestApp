package com.example.foodtestapp.ui.product

import com.example.foodtestapp.models.FilterTag
import com.example.foodtestapp.models.Product

interface ProductCountRepository {
    var allProducts: Map<Int, Product>
    var totalPrice: Double
    val savedCountMap: MutableMap<Int, Int>
    var nowProduct: Product
    val filterTags: Map<Int, FilterTag>

    fun getCountById(id: Int): Int
    fun getNowProduct(productId: Int): Product
    fun changeProductCountMap(productId: Int, count: Int)
    fun getSaveCount(saveCount: Int, isMinus: Boolean): Int
}