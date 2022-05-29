@file:Suppress("SameParameterValue", "MemberVisibilityCanBePrivate")

package org.llamarama.petty.blocks

import org.llamarama.petty.MainFile
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.MapColor
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object PettyBlocks {
    private val BlockItemsRegistry = linkedMapOf<String, Item>()
    private val BlockRegistry = linkedMapOf<String, Block>()

    val COOL_BLOCK: Block
    val BIRD_CAGE: Block
    val PILLOW: Block

    /**
     * Register blocks in here.
     * [net.minecraft.item.BlockItem]'s gets added automatically (but can't be referenced atm).
     * If you wish to change the settings of the BlockItem implement your own methods for it.
     */
    init {
        COOL_BLOCK = addBlock("coolblock", Block(AbstractBlock.Settings.copy(Blocks.STONE)))
        BIRD_CAGE = addSpecialItemBlock("cage_block", CageBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO).breakInstantly().mapColor(MapColor.PALE_YELLOW)))
        PILLOW = addBlock("pillow", PillowBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)))
    }

    private fun addBlock(name: String, block: Block): Block {
        val correctedName = name.replace(" ", "").lowercase().trim()
        BlockRegistry[correctedName] = block
        BlockItemsRegistry[correctedName + "_item"] =
            (BlockItem(block, Item.Settings().maxCount(64).group(ItemGroup.MISC)))
        return block
    }

    private fun addSpecialItemBlock(name: String, block: Block): Block {
        val correctedName = name.replace(" ", "").lowercase().trim()
        BlockRegistry[correctedName] = block
        return block
    }

    fun registerBlocks() {
        BlockRegistry.forEach { (name, block) ->
            Registry.register(Registry.BLOCK, Identifier(MainFile.MOD_ID, name), block)
        }
        BlockItemsRegistry.forEach { (name, item) ->
            Registry.register(Registry.ITEM, Identifier(MainFile.MOD_ID, name), item)

        }
    }
}