package com.dhkpo.itemservice.domain.item

class Item(
    var itemName: String,
    var price: Int,
    var quantity: Int = 0
) {
    var id: Long = 0L
}
