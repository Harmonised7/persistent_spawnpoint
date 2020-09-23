package harmonised.pspawn.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import harmonised.pspawn.events.PlayerHandler;
import harmonised.pspawn.util.LogHandler;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.dimension.DimensionType;


public class PSpawnSingleCommand
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

        if( sender.world.dimension.getType().equals(DimensionType.OVERWORLD ) )
            PlayerHandler.setSpawnpoint( sender );
        else
        {
            sender.sendMessage( new TranslationTextComponent( "pspawn.onlyAvailableInOverworld" ) );
            LogHandler.LOGGER.error( "PSpawn was called not from Overworld, which is not supported");
        }

        return 1;
    }
}