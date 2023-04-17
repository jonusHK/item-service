package com.dhkpo.itemservice.web.basic

import com.dhkpo.itemservice.domain.item.Item
import com.dhkpo.itemservice.domain.item.ItemRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/basic/items")
class BasicItemController(private val itemRepository: ItemRepository) {

    @GetMapping
    fun items(model: Model): String {
        val items: List<Item> = itemRepository.findAll()
        model.addAttribute("items", items)
        return "basic/items"
    }

    @GetMapping("/{itemId}")
    fun item(@PathVariable itemId: Long, model: Model): String {
        val item: Item? = itemRepository.findById(itemId)
        model.addAttribute("item", item)
        return "basic/item"
    }

    @GetMapping("/add")
    fun addForm(): String {
        return "basic/addForm"
    }

//    @PostMapping("/add")
    fun addItemV1(
        @RequestParam itemName: String,
        @RequestParam price: Int,
        @RequestParam quantity: Int,
        model: Model
    ): String {
        val item = Item(itemName, price, quantity)

        itemRepository.save(item)
        model.addAttribute("item", item)

        return "basic/item"
    }

//    @PostMapping("/add")
    fun addItemV2(@ModelAttribute("item") item: Item): String {
        itemRepository.save(item)

        return "basic/item"
    }

//    @PostMapping("/add")
    fun addItemV3(@ModelAttribute item: Item): String {
        itemRepository.save(item)

        return "basic/item"
    }

//    @PostMapping("/add")
    fun addItemV4(item: Item): String {
        itemRepository.save(item)

        return "basic/item"
    }

//    @PostMapping("/add")
    fun addItemV5(item: Item): String {
        itemRepository.save(item)

        return "redirect:/basic/items/${item.id}"
    }

    @PostMapping("/add")
    fun addItemV6(item: Item, redirectAttribute: RedirectAttributes): String {
        val savedItem: Item = itemRepository.save(item)
        redirectAttribute.addAttribute("itemId", savedItem.id)
        redirectAttribute.addAttribute("status", true)
        return "redirect:/basic/items/{itemId}"
    }

    @GetMapping("/{itemId}/edit")
    fun editForm(@PathVariable itemId: Long, model: Model): String {
        val item: Item? = itemRepository.findById(itemId)
        model.addAttribute("item", item)
        return "basic/editForm"
    }

    @PostMapping("{itemId}/edit")
    fun edit(@PathVariable itemId: Long, @ModelAttribute item: Item): String {
        itemRepository.update(itemId, item)
        return "redirect:/basic/items/{itemId}"
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    fun init() {
        itemRepository.save(Item("itemA", 10000, 10))
        itemRepository.save(Item("itemB", 20000, 20))
    }
}
