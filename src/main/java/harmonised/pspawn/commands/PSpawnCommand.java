package harmonised.pspawn.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;

public class PSpawnCommand
{
    public static void register( CommandDispatcher<CommandSource> dispatcher )
    {
        dispatcher.register( Commands.literal( "pspawn" )
        .executes( PSpawnSingleCommand::execute )
        .requires( player -> player.hasPermissionLevel( 2 ))
        .then( Commands.argument( "target", EntityArgument.players())
        .executes( PSpawnSingleCommand::execute )
        ));
    }
}
