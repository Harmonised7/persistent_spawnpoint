package harmonised.pspawn.pspawn_saved_data;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public class SpawnData
{
    public BlockPos pos;
    public DimensionType dimType;

    public SpawnData( BlockPos pos, DimensionType dimType )
    {
        this.pos = pos;
        this.dimType = dimType;
    }
}
