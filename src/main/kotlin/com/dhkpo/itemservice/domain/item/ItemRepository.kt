package com.dhkpo.itemservice.domain.item

import org.springframework.stereotype.Repository

@Repository
class ItemRepository {
    companion object {
        @JvmStatic
        private val store = HashMap<Long, Item>()

        @JvmStatic
        private var sequence = 0L
    }

    fun save(item: Item): Item {
        item.id = ++sequence
        store[item.id] = item
        return item
    }

    fun findById(id: Long): Item? {
        return store[id]
    }

    fun findAll(): List<Item> {
        return ArrayList(store.values)
    }

    // UpdateItemDto 추가하는게 좋음
    fun update(itemId: Long, updateParam: Item) {
        val findItem: Item? = findById(itemId)
        findItem?.let {
            findItem.itemName = updateParam.itemName
            findItem.price = updateParam.price
            findItem.quantity = updateParam.quantity
        }
    }

    fun clearStore() {
        store.clear()
    }
}
