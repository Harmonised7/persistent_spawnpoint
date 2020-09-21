package harmonised.pspawn.pspawn_saved_data;

import harmonised.pspawn.util.LogHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.WorldSavedData;

public class PSpawnSavedData extends WorldSavedData
{
    public static MinecraftServer server;
    private static String NAME = "pspawn";
    public PSpawnSavedData()
    {
        super( NAME );
    }

    @Override
    public void read( CompoundNBT inData )
    {
    }

    @Override
    public CompoundNBT write( CompoundNBT outData )
    {
        return outData;
    }

    public static PSpawnSavedData get()
    {
        return server.getWorld( DimensionType.OVERWORLD ).getSavedData().getOrCreate( PSpawnSavedData::new, NAME );
    }

    public static PSpawnSavedData get( PlayerEntity player )
    {
        if( player.getServer() == null )
            LogHandler.LOGGER.error( "FATAL PSPAWN ERROR: SERVER IS NULL. Could not get PSpawnSavedData" );

        return player.getServer().getWorld( DimensionType.OVERWORLD ).getSavedData().getOrCreate( PSpawnSavedData::new, NAME );
    }
}
