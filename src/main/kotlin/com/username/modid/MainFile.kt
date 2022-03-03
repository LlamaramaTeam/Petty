package com.username.modid

import com.username.modid.blocks.ModIdBlocks
import com.username.modid.items.ModIdItems
import net.fabricmc.api.ModInitializer


/**
 * Main File
 * VM options for mixins:
 * -Dmixin.debug.export=true (exports mixins into run/mixin.out/)
 * -Dmixin.debug=true (turns on all debugging features)
 */
@Suppress("UNUSED")
object MainFile : ModInitializer {
    const val MOD_ID = "mod_id"


    override fun onInitialize() {
        ModIdBlocks.registerBlocks()
        ModIdItems.registerItems()
    }
}
