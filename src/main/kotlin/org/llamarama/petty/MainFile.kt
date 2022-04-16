@file:JvmName("PettyMain")
package org.llamarama.petty

import net.fabricmc.api.ModInitializer
import org.llamarama.petty.blocks.registerBlocks
import org.llamarama.petty.items.registerItems

const val MOD_ID = "petty"

/**
 * Main File
 * VM options for mixins:
 * -Dmixin.debug.export=true (exports mixins into run/mixin.out/)
 * -Dmixin.debug=true (turns on all debugging features)
 */
@Suppress("UNUSED")
object MainFile : ModInitializer {


    override fun onInitialize() {
        registerBlocks()
        registerItems()
    }
}
