package org.llamarama.petty.worldgen.decorators

import net.minecraft.world.gen.treedecorator.TreeDecoratorType
import org.llamarama.petty.mixins.TreeDecoratorTypeMixin

object DecoratorTypes {
    val TREE_HOLE_DECORATOR: TreeDecoratorType<TreeHoleDecorator> =
        TreeDecoratorTypeMixin.callRegister("tree_hole_decorator", codec)
}