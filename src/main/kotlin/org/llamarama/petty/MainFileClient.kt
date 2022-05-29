package org.llamarama.petty

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.block.BlockState
import net.minecraft.client.MinecraftClient
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView
import org.llamarama.petty.blocks.PettyBlocks


@Environment(EnvType.CLIENT)
object MainFileClient : ClientModInitializer {

    override fun onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), PettyBlocks.BIRD_CAGE)

        ColorProviderRegistry.BLOCK.register(BlockColorProvider { state: BlockState?, world: BlockRenderView?, pos: BlockPos?, tintIndex: Int ->
            if (pos != null && world != null) MinecraftClient.getInstance().blockColors.getParticleColor(world.getBlockState(pos.down()), MinecraftClient.getInstance().world, pos.down()) - 5
            else 0
        }, PettyBlocks.PILLOW)
    }
}