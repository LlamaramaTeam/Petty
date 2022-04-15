package org.llamarama.petty.mixins;

import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import org.llamarama.petty.worldgen.decorators.TreeHoleDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

// This mixin is only for debug purposes
@Deprecated()
@Mixin(TreeConfiguredFeatures.class)
public class ConfiguredFeaturesMixin {

    /**
     * Debug method to replace existing bee decorators with the custom TreeHoleDecorator
     * In the future, we want to change this to ADD the TreeHole decorator to the list
     * of decorators instead of replacing it
     */
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/gen/feature/TreeFeatureConfig$Builder;decorators(Ljava/util/List;)Lnet/minecraft/world/gen/feature/TreeFeatureConfig$Builder;"
            )
    )
    private static TreeFeatureConfig.Builder proxyDebug(TreeFeatureConfig.Builder builder, List<TreeDecorator> decorators) {
        if (decorators.size() > 0 && decorators.get(0) instanceof BeehiveTreeDecorator) {
            return builder.decorators(List.of(new TreeHoleDecorator()));
        }
        return builder.decorators(decorators);
    }
}
