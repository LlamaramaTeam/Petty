@file:Suppress("SameParameterValue", "MemberVisibilityCanBePrivate")

package com.username.modid.items

import com.username.modid.MainFile
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModIdItems {
    private val ItemRegistry = linkedMapOf<String, Item>()

    val COOL_ITEM: Item

    /**
     * Register [net.minecraft.item.Item]'s in here.
     */
    init {
        COOL_ITEM = addItem("coolitem", Item(Item.Settings().maxCount(64).group(ItemGroup.MISC)))
    }

    private fun <I: Item> addItem(name: String, item: I): I {
        val correctedName = name.replace(" ", "").lowercase().trim()
        ItemRegistry[correctedName] = item
        return item
    }

    fun registerItems() {
        this.ItemRegistry.forEach {
            Registry.register(Registry.ITEM, Identifier(MainFile.MOD_ID, it.key), it.value)
        }
    }
}