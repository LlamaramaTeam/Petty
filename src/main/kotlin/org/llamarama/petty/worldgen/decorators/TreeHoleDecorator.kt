package org.llamarama.petty.worldgen.decorators

import com.mojang.serialization.Codec
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.TestableWorld
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecoratorType
import org.llamarama.petty.blocks.PettyBlocks
import java.util.*
import java.util.function.BiConsumer

val codec = Codec.unit { INSTANCE }
private val INSTANCE = TreeHoleDecorator()
private val FACE_DIRECTION = Direction.SOUTH

class TreeHoleDecorator() : TreeDecorator() {

    override fun getType(): TreeDecoratorType<*> {
        return DecoratorTypes.TREE_HOLE_DECORATOR
    }

    /**
     * 5% chance
     */
    override fun generate(
        world: TestableWorld?,
        replacer: BiConsumer<BlockPos, BlockState>?,
        random: Random?,
        logPositions: MutableList<BlockPos>?,
        leavesPositions: MutableList<BlockPos>?,
    ) {
        // do nothing if the random number does not fulfill the 5% chance
        // In the 2 tests I made so far this was why too high
        if (random!!.nextInt() >= 5) return

        // from BeehiveTreeDecorator
        val i =
            if (!leavesPositions!!.isEmpty())
                (leavesPositions[0].y - 1).coerceAtLeast(logPositions!![0].y + 1)
            else
                (logPositions!![0].y + 1 + random.nextInt(3)).coerceAtMost(logPositions[logPositions.size - 1].y)
        logPositions.stream().filter { pos: BlockPos -> pos.y == i }
            // filter out the positions which don't have air in front of them
            .filter { Feature.isAir(world, it.offset(FACE_DIRECTION)) }
            // then find the first one
            .findFirst()
            // then map over the optional replacing it with a tree hole block
            .map { pos: BlockPos ->
                replacer!!.accept(pos, PettyBlocks.COOL_BLOCK.defaultState)
                pos
            }
    }
}