package harmonised.pspawn.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import harmonised.pspawn.events.PlayerHandler;
import harmonised.pspawn.util.LogHandler;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DimensionType;

import java.util.Collection;

public class PSpawnMultiCommand
{
    public static int execute(CommandContext<CommandSource> context) throws CommandException
    {
        Collection<ServerPlayerEntity> targets;
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

        if( sender.world.getDimensionType().equals( DimensionType.OVERWORLD ) )
            PlayerHandler.setSpawnpoint( sender );
        else
        {
            sender.sendStatusMessage( new TranslationTextComponent( "pspawn.onlyAvailableInOverworld" ), false );
            LogHandler.LOGGER.error( "PSpawn was called not from Overworld, which is not supported");
        }

        try
        {
            targets = EntityArgument.getPlayers( context, "target" );
        }
        catch( CommandSyntaxException e )
        {
            return 0;
        }

        for( ServerPlayerEntity player : targets )
        {
            PlayerHandler.setSpawnpoint( player );
        }

        return 1;
    }
}