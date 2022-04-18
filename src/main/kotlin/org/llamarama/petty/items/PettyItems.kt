@file:JvmName("PettyItems")
@file:Suppress("SameParameterValue", "MemberVisibilityCanBePrivate")

package org.llamarama.petty.items

import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.llamarama.petty.MOD_ID

private val ItemRegistry = linkedMapOf<String, Item>()


/**
 * Register [net.minecraft.item.Item]'s under here.
 */
val COOL_ITEM = addItem("coolitem", Item(Item.Settings().maxCount(64).group(ItemGroup.MISC)))

private fun addItem(name: String, item: Item): Item {
    val correctedName = name.replace(" ", "").lowercase().trim()
    ItemRegistry[correctedName] = item
    return item
}

fun registerItems() {
    ItemRegistry.forEach { (name, item) ->
        Registry.register(Registry.ITEM, Identifier(MOD_ID, name), item)
    }
}