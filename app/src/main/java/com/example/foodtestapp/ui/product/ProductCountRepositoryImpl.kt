package com.example.foodtestapp.ui.product

import com.example.domain.utils.ACUTE_STRING
import com.example.domain.utils.DEFAULT_DOUBLE
import com.example.domain.utils.DEFAULT_INT
import com.example.domain.utils.DISCOUNT_STRING
import com.example.domain.utils.WITHOUT_MEAT_STRING
import com.example.foodtestapp.models.FilterTag
import com.example.foodtestapp.models.Product

class ProductCountRepositoryImpl : ProductCountRepository {
    override var allProducts: Map<Int, Product> = mutableMapOf()
    override var totalPrice = DEFAULT_DOUBLE
    override val savedCountMap = mutableMapOf<Int, Int>()
    override var nowProduct = Product.unknown
    override val filterTags: Map<Int, FilterTag> = mapOf(
        2 to FilterTag(id = 2, title = WITHOUT_MEAT_STRING),
        4 to FilterTag(id = 4, title = ACUTE_STRING),
        6 to FilterTag(id = 6, title = DISCOUNT_STRING),
    )

    override fun getCountById(id: Int): Int = savedCountMap[id] ?: DEFAULT_INT
    override fun getNowProduct(productId: Int): Product = allProducts[productId] ?: Product.unknown
    override fun changeProductCountMap(productId: Int, count: Int) {
        if (count > 0) savedCountMap[productId] = count else savedCountMap.remove(productId)
    }

    override fun getSaveCount(saveCount: Int, isMinus: Boolean): Int = if (!isMinus) saveCount + 1
    else if (saveCount > 0) saveCount - 1 else DEFAULT_INT
}