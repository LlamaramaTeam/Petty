@file:JvmName("PettyBlocks")
@file:Suppress("SameParameterValue", "MemberVisibilityCanBePrivate")

package org.llamarama.petty.blocks

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.llamarama.petty.MOD_ID

private val BlockItemsRegistry = linkedMapOf<String, Item>()
private val BlockRegistry = linkedMapOf<String, Block>()

/**
 * Register blocks under here.
 */
val COOL_BLOCK = addBlock("coolblock", Block(AbstractBlock.Settings.copy(Blocks.STONE)))

private fun addBlock(name: String, block: Block): Block {
    val correctedName = name.replace(" ", "").lowercase().trim()
    BlockRegistry[correctedName] = block
    BlockItemsRegistry[correctedName + "_item"] =
        (BlockItem(block, Item.Settings().maxCount(64).group(ItemGroup.MISC)))
    return block
}

fun registerBlocks() {
    BlockRegistry.forEach { (name, block) ->
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, name), block)
    }
    BlockItemsRegistry.forEach { (name, item) ->
        Registry.register(Registry.ITEM, Identifier(MOD_ID, name), item)

    }
}