package harmonised.pspawn.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PSpawnCommand
{
    public static void register( CommandDispatcher<CommandSource> dispatcher )
    {
        dispatcher.register( Commands.literal( "pspawn" ) );
    }
}
