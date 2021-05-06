package com.beatrix.debug.utils

// class used for items in the recycler view
data class Expenses(
    var category: String,
    var amount: Double,
    var description: String,
    var categoryImage: Int
) {}