package harmonised.pspawn.events;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventHandler
{
//    @SubscribeEvent
//    public static void playerLoggedIn( PlayerEvent.PlayerLoggedInEvent event )
//    {
//        PlayerHandler.playerLoggedIn( event );
//    }

    @SubscribeEvent
    public static void livingDeath( LivingDeathEvent event )
    {
        PlayerHandler.handleLivingDeath( event );
    }
}
