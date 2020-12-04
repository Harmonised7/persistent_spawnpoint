package harmonised.pspawn.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import harmonised.pspawn.events.PlayerHandler;
import harmonised.pspawn.util.LogHandler;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;


public class PSpawnntCommand
{
    public static int execute(CommandContext<CommandSource> context) throws CommandException
    {
        ServerPlayerEntity sender;

        try
        {
            sender = context.getSource().asPlayer();
        }
        catch( CommandSyntaxException e )
        {
            LogHandler.LOGGER.error( "SetSpawnPointCommand sent not from a Player" );
            return 0;
        }

        PlayerHandler.removeSpawnpoint( sender );

        return 1;
    }
}