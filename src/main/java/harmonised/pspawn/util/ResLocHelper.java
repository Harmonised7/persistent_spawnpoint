package harmonised.pspawn.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ResLocHelper
{
    public static ResourceLocation getDimensionResLoc(World world )
    {
        return world.func_241828_r().func_230520_a_().getKey( world.getDimensionType() );
    }
}
