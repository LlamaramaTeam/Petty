package org.llamarama.petty.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.TameableShoulderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.llamarama.petty.blocks.CageBlock;
import org.llamarama.petty.blocks.ModIdBlocks;
import org.llamarama.petty.items.ModIdItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ParrotEntity.class)
public abstract class ParrotEntityMixin extends TameableShoulderEntity implements Flutterer {
    private int featherDrop = 10;

    public ParrotEntityMixin(EntityType<? extends ParrotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "interactMob", cancellable = true)
    public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        BlockPos cagePos = this.getBlockPos();
        if (player.getStackInHand(hand).isOf(ModIdBlocks.INSTANCE.getBIRD_CAGE().asItem())) {
            if (this.isSitting() || this.isInSittingPose()) {
                this.teleport(cagePos.getX() + 0.5, cagePos.getY() + 0.3, cagePos.getZ() + 0.5);
                this.setVelocity(0, 0, 0);
                this.setSitting(false);
                CageBlock.placeAt(world, ModIdBlocks.INSTANCE.getBIRD_CAGE().getDefaultState(), cagePos, 27);
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }

        
    }

    @Override
    public void tick() {
        if (featherDrop <= 0) {
            if (world.getBlockState(this.getBlockPos().up()).isOf(ModIdBlocks.INSTANCE.getBIRD_CAGE())) {
                ItemScatterer.spawn(world, this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(),
                        new ItemStack(ModIdItems.INSTANCE.getPARROT_FEATHER()));
            }
            featherDrop = 4000;
        } else {
            featherDrop--;
        }
        super.tick();
    }
}