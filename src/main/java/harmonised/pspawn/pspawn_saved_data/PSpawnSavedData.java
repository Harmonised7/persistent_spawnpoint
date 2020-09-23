package harmonised.pspawn.pspawn_saved_data;

import harmonised.pspawn.util.LogHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.WorldSavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PSpawnSavedData extends WorldSavedData
{
    private static String NAME = "pspawn";
    private Map<UUID, SpawnData> spawnMap = new HashMap<>();
    public PSpawnSavedData()
    {
        super( NAME );
    }

    @Override
    public void read( CompoundNBT inData )
    {
//        if( inData.contains( "defaultSpawnPos" ) )
//            defaultSpawnPoint = NBTUtil.readBlockPos( inData.getCompound( "defaultSpawnPos" ) );
        if( inData.contains( "spawnpoints" ) )
        {
            CompoundNBT spawnpointNBT;
            UUID uuid;
            BlockPos pos;
            DimensionType dimType;
            for( String uuidKey : inData.getCompound( "spawnpoints" ).keySet() )
            {
                uuid = UUID.fromString( uuidKey );
                spawnpointNBT = inData.getCompound( "spawnpoints" ).getCompound( uuidKey );
                pos = NBTUtil.readBlockPos( spawnpointNBT.getCompound( "pos" ) );
                dimType = DimensionType.byName( new ResourceLocation( spawnpointNBT.getString( "dimKey" ) ) );
                if( dimType != null )
                {
                    spawnMap.put( uuid, new SpawnData( pos, dimType ) );
                }
            }
        }
    }

    @Override
    public CompoundNBT write( CompoundNBT outData )
    {
        outData.put( "spawnpoints", new CompoundNBT() );
        CompoundNBT playerSpawnData;

        for( Map.Entry<UUID, SpawnData> entry : spawnMap.entrySet() )
        {
            playerSpawnData = new CompoundNBT();
            playerSpawnData.putString( "dimKey", entry.getValue().dimType.toString() );
            playerSpawnData.put( "pos", NBTUtil.writeBlockPos( entry.getValue().pos ) );
            outData.put( entry.getKey().toString(), playerSpawnData );
        }

        return outData;
    }

    public static PSpawnSavedData get( PlayerEntity player )
    {
        if( player.getServer() == null )
            LogHandler.LOGGER.error( "FATAL PSPAWN ERROR: SERVER IS NULL. Could not get PSpawnSavedData" );

        return player.getServer().getWorld( DimensionType.OVERWORLD ).getSavedData().getOrCreate( PSpawnSavedData::new, NAME );
    }

    public void setSpawnPoint( UUID uuid, BlockPos newPos, DimensionType dimType )
    {
        spawnMap.put( uuid, new SpawnData( newPos, dimType ) );
        this.markDirty();
    }

    public SpawnData getSpawnData( UUID uuid )
    {
        return spawnMap.get( uuid );
    }

//    public BlockPos getDefaultSpawnPoint()
//    {
//        return defaultSpawnPoint == null ? new BlockPos( Config.forgeConfig.defaultSpawnX.get(), Config.forgeConfig.defaultSpawnY.get(), Config.forgeConfig.defaultSpawnZ.get() ) : defaultSpawnPoint;
//    }
}
