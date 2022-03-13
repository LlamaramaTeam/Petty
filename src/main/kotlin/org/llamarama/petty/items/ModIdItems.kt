@file:Suppress("SameParameterValue", "MemberVisibilityCanBePrivate")

package org.llamarama.petty.items

import net.minecraft.item.BlockItem
import org.llamarama.petty.MainFile
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.llamarama.petty.blocks.ModIdBlocks

object ModIdItems {
    private val ItemRegistry = linkedMapOf<String, Item>()

    val COOL_ITEM: Item
    val CAGE_BLOCK_ITEM: BlockItem

    /**
     * Register [net.minecraft.item.Item]'s in here.
     */
    init {
        COOL_ITEM = addItem("coolitem", Item(Item.Settings().maxCount(64).group(ItemGroup.MISC)))
        CAGE_BLOCK_ITEM = addItem("cage_block", BlockItem(ModIdBlocks.BIRD_CAGE, Item.Settings().maxCount(64).group(ItemGroup.MISC)))
    }

    private fun <I: Item> addItem(name: String, item: I): I {
        val correctedName = name.replace(" ", "").lowercase().trim()
        ItemRegistry[correctedName] = item
        return item
    }

    fun registerItems() {
        ItemRegistry.forEach {
            Registry.register(Registry.ITEM, Identifier(MainFile.MOD_ID, it.key), it.value)
        }
    }
}