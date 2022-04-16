package org.llamarama.petty.blocks

import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.TallPlantBlock
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.entity.Entity
import net.minecraft.entity.passive.ParrotEntity
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World

open class CageBlock(settings: Settings?) : TallPlantBlock(settings) {
    private val half: EnumProperty<DoubleBlockHalf>? = Properties.DOUBLE_BLOCK_HALF

    private val topShape = VoxelShapes.union(
        createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 1.0),
        createCuboidShape(0.0, 0.0, 0.0, 1.0, 16.0, 16.0),
        createCuboidShape(15.0, 0.0, 0.0, 16.0, 16.0, 16.0),
        createCuboidShape(0.0, 0.0, 15.0, 16.0, 16.0, 16.0),
        createCuboidShape(0.0, 15.0, 0.0, 16.0, 16.0, 16.0)
    )

    private val bottomShape = VoxelShapes.union(
        createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 1.0),
        createCuboidShape(0.0, 0.0, 0.0, 1.0, 16.0, 16.0),
        createCuboidShape(15.0, 0.0, 0.0, 16.0, 16.0, 16.0),
        createCuboidShape(0.0, 0.0, 15.0, 16.0, 16.0, 16.0),
        createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0)
    )

    override fun getCollisionShape(state: BlockState?, world: BlockView?, pos: BlockPos?, context: ShapeContext?): VoxelShape {
        val isTop : DoubleBlockHalf? = state?.get(half)
        return if (isTop == DoubleBlockHalf.UPPER) topShape else bottomShape
    }

    override fun getOutlineShape(state: BlockState?, world: BlockView?, pos: BlockPos?, context: ShapeContext?): VoxelShape {
        val isTop : DoubleBlockHalf? = state?.get(half)
        return if (isTop == DoubleBlockHalf.UPPER) topShape else bottomShape
    }

    override fun getOffsetType(): OffsetType {
        return OffsetType.NONE
    }
}




