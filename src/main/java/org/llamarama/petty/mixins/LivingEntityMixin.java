package org.llamarama.petty.mixins;

import net.minecraft.block.Blocks;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.TameableShoulderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.llamarama.petty.blocks.CageBlock;
import org.llamarama.petty.blocks.ModIdBlocks;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ParrotEntity.class)
public abstract class LivingEntityMixin extends TameableShoulderEntity implements Flutterer {
    public LivingEntityMixin(EntityType<? extends ParrotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        BlockPos cagePos = this.getBlockPos();
        if (player.getStackInHand(hand).isOf(ModIdBlocks.INSTANCE.getBIRD_CAGE().asItem())) {
            this.teleport(cagePos.getX() + 0.5, cagePos.getY() + 0.3, cagePos.getZ() + 0.5);
            this.setVelocity(0, 0, 0);
            CageBlock.placeAt(world, ModIdBlocks.INSTANCE.getBIRD_CAGE().getDefaultState(), cagePos, 27);
            return ActionResult.SUCCESS;
        } else {
            return super.interactMob(player, hand);
        }
    }
}
