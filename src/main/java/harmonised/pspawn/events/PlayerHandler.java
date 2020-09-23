package harmonised.pspawn.events;

import harmonised.pspawn.pspawn_saved_data.PSpawnSavedData;
import harmonised.pspawn.pspawn_saved_data.SpawnData;
import harmonised.pspawn.util.LogHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class PlayerHandler
{
    public static void handleLivingDeath( LivingDeathEvent event )
    {
        if( event.getEntityLiving() instanceof PlayerEntity )
        {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
            SpawnData spawnData = PSpawnSavedData.get( player ).getSpawnData( player.getUniqueID() );

            if( spawnData != null )
            {
                BlockPos spawnPos = spawnData.pos;
                if( player.world.dimension.getType().equals( spawnData.dimType ) )
                {
                    player.setPositionAndRotation( spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0, 0 );
                    spawnPos = getSafePos( player );
                }
                player.setSpawnPoint( spawnPos, true, true, spawnData.dimType );
            }
        }
    }

    public static BlockPos getSafePos( Entity entity )
    {
        World world = entity.world;
        BlockPos pos = entity.getPosition();

        while( !world.hasNoCollisions( entity ) && pos.getY() <= world.getMaxHeight() )
        {
            pos = pos.up();
            entity.setPosition( pos.getX(), pos.getY(), pos.getZ() );
        }

        while( !world.hasNoCollisions( entity ) && pos.getY() > 0 )
        {
            pos = pos.down();
            entity.setPosition( pos.getX(), pos.getY(), pos.getZ() );
        }

        return pos;
    }
    
    public static void setSpawnpoint( ServerPlayerEntity player )
    {
        setSpawnpoint( player, player.getPosition(), player.world.dimension.getType() );
    }

    public static void setSpawnpoint( ServerPlayerEntity player, BlockPos pos, DimensionType dimType )
    {
        PSpawnSavedData.get( player ).setSpawnPoint( player.getUniqueID(), pos, dimType );
        player.sendMessage( new TranslationTextComponent( "pspawn.spawnpointSet", pos.getX(), pos.getY(), pos.getZ(), new TranslationTextComponent( player.dimension.getRegistryName().toString() ).getString() ) );
    }
}
