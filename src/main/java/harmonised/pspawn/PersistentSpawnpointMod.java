package harmonised.pspawn;

import harmonised.pspawn.commands.PSpawnCommand;
import harmonised.pspawn.config.Config;
import harmonised.pspawn.util.Reference;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod( Reference.MOD_ID )
public class PersistentSpawnpointMod
{
    public PersistentSpawnpointMod()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener( this::modsLoading );

        PSpawnCommand.init();
        Config.init();
    }

    private void modsLoading( FMLCommonSetupEvent event )
    {
        MinecraftForge.EVENT_BUS.register( harmonised.pspawn.events.EventHandler.class );
    }
}
