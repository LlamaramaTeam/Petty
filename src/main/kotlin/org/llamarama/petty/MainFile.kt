package org.llamarama.petty

import org.llamarama.petty.blocks.ModIdBlocks
import org.llamarama.petty.items.ModIdItems
import net.fabricmc.api.ModInitializer


/**
 * Main File
 * VM options for mixins:
 * -Dmixin.debug.export=true (exports mixins into run/mixin.out/)
 * -Dmixin.debug=true (turns on all debugging features)
 */
@Suppress("UNUSED")
object MainFile : ModInitializer {
    const val MOD_ID = "petty"


    override fun onInitialize() {
        ModIdBlocks.registerBlocks()
        ModIdItems.registerItems()
    }
}
