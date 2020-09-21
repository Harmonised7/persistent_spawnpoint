package harmonised.pspawn.config;

import harmonised.pspawn.pspawn_saved_data.PSpawnSavedData;
import harmonised.pspawn.skills.Skill;
import harmonised.pspawn.util.XP;
import harmonised.pspawn.util.LogHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

import java.util.HashMap;
import java.util.Map;

public class Config
{
    public static Map<String, Double> localConfig = new HashMap<>();
    private static Map<String, Double> config = new HashMap<>();

    //Client only, too lazy to put it somewhere better
    private static final Map<String, Double> abilities = new HashMap<>();
    private static Map<String, Double> preferences = new HashMap<>();

    public static ConfigImplementation forgeConfig;

    public static void init()
    {
        forgeConfig = ConfigHelper.register( ModConfig.Type.COMMON, ConfigImplementation::new );
    }

    public static class ConfigImplementation
    {
        //General
        public ConfigHelper.ConfigValueListener<Double> defaultRandomSpawnRadius;

        public ConfigImplementation(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
        {
            builder.push( "General" );
            {
                this.defaultRandomSpawnRadius = subscriber.subscribe(builder
                        .comment( "How far from the Origin of the set Spawnpoint should players be able to spawn by default?" )
                        .translation( "pspawn.defaultRandomSpawnRadius" )
                        .define( "defaultRandomSpawnRadius", false ) );

                builder.pop();
            }
        }
    }

    public static double getConfig( String key )
    {
        if( Config.config.containsKey( key ) )
            return Config.config.get( key );
        else if( Config.localConfig.containsKey( key ) )
            return Config.localConfig.get( key );
        else
        {
            LogHandler.LOGGER.error( "UNABLE TO READ PMMO CONFIG \"" + key + "\" PLEASE REPORT (This is normal during boot if JEI is installed)" );
            return -1;
        }
    }

    public static Map<Skill, Double> getXpMap( PlayerEntity player )
    {
        if( player.world.isRemote() )
            return XP.getOfflineXpMap( player.getUniqueID() );
        else
            return PSpawnSavedData.get( player ).getXpMap( player.getUniqueID() );
    }

    public static Map<String, Double> getConfigMap()
    {
        return config;
    }

    public static void setConfigMap( Map<String, Double> inMap )
    {
        config = inMap;
    }

    public static Map<String, Double> getPreferencesMap( PlayerEntity player )
    {
        if( player.world.isRemote() )
            return preferences;
        else
            return PSpawnSavedData.get( player ).getPreferencesMap( player.getUniqueID() );
    }

    public static Map<String, Double> getPreferencesMapOffline()
    {
        return preferences;
    }

    public static Map<String, Double> getAbilitiesMap( PlayerEntity player )
    {
        if( player.world.isRemote() )
            return abilities;
        else
            return PSpawnSavedData.get( player ).getAbilitiesMap( player.getUniqueID() );
    }

    public static void setPreferencesMap( Map<String, Double> newPreferencesMap )
    {
        preferences = newPreferencesMap;
    }
}
