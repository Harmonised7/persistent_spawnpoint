//package harmonised.pspawn.config;
//
//import net.minecraftforge.common.ForgeConfigSpec;
//import net.minecraftforge.fml.config.ModConfig;
//
//public class Config
//{
//    public static ConfigImplementation forgeConfig;
//
//    public static void init()
//    {
//        forgeConfig = ConfigHelper.register( ModConfig.Type.COMMON, ConfigImplementation::new );
//    }
//
//    public static class ConfigImplementation
//    {
//        //General
//        public ConfigHelper.ConfigValueListener<Double> defaultRandomSpawnRadius;
//        public ConfigHelper.ConfigValueListener<Double> defaultSpawnX;
//        public ConfigHelper.ConfigValueListener<Double> defaultSpawnY;
//        public ConfigHelper.ConfigValueListener<Double> defaultSpawnZ;
//
//        public ConfigImplementation(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
//        {
//            builder.push( "General" );
//            {
//                this.defaultRandomSpawnRadius = subscriber.subscribe(builder
//                        .comment( "How far from the Origin of the set Spawnpoint should players be able to spawn by default?" )
//                        .translation( "pspawn.defaultRandomSpawnRadius" )
//                        .defineInRange( "defaultRandomSpawnRadius", 50D, 0, 10000000 ) );
//
//                this.defaultSpawnX = subscriber.subscribe(builder
//                        .comment( "Where on the X should the players without a set spawn position spawn at?" )
//                        .translation( "pspawn.defaultSpawnX" )
//                        .defineInRange( "defaultSpawnX", 0D, -10000000, 10000000 ) );
//
//                this.defaultSpawnY = subscriber.subscribe(builder
//                        .comment( "Where on the Y should the players without a set spawn position spawn at?" )
//                        .translation( "pspawn.defaultSpawnY" )
//                        .defineInRange( "defaultSpawnY", 64D, -10000000, 10000000 ) );
//
//                this.defaultSpawnZ = subscriber.subscribe(builder
//                        .comment( "Where on the Z should the players without a set spawn position spawn at?" )
//                        .translation( "pspawn.defaultSpawnZ" )
//                        .defineInRange( "defaultSpawnZ", 0D, -10000000, 10000000 ) );
//
//                builder.pop();
//            }
//        }
//    }
//}
