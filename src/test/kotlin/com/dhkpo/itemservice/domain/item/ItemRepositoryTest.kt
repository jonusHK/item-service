package com.dhkpo.itemservice.domain.item

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test


class ItemRepositoryTest {

    private val itemRepository = ItemRepository()

    @AfterEach
    fun afterEach() {
        itemRepository.clearStore()
    }

    @Test
    fun save() {
        //given
        val item = Item("itemA", 10000, 10)

        //when
        val savedItem: Item = itemRepository.save(item)

        //then
        val findItem: Item? = itemRepository.findById(item.id)
        assertThat(findItem).isEqualTo(savedItem)
    }

    @Test
    fun findAll() {
        //given
        val item1 = Item("item1", 10000, 10)
        val item2 = Item("item2", 20000, 20)

        itemRepository.save(item1)
        itemRepository.save(item2)

        //when
        val result: List<Item> = itemRepository.findAll()

        //then
        assertThat(result.size).isEqualTo(2)
        assertThat(result).contains(item1, item2)
    }

    @Test
    fun updateItem() {
        //given
        val item = Item("item1", 10000, 10)

        val savedItem: Item = itemRepository.save(item)
        val itemId: Long = savedItem.id

        //when
        val updateParam = Item("item2", 20000, 30)
        itemRepository.update(itemId, updateParam)

        //then
        val findItem: Item? = itemRepository.findById(itemId)

        assertThat(findItem).isNotNull
        assertThat(findItem!!.itemName).isEqualTo(updateParam.itemName)
        assertThat(findItem.price).isEqualTo(updateParam.price)
        assertThat(findItem.quantity).isEqualTo(updateParam.quantity)
    }
}