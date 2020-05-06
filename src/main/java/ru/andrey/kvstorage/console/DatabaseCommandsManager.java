package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.console.commands.CreateDatabaseCommand;
import ru.andrey.kvstorage.console.commands.CreateTableCommand;
import ru.andrey.kvstorage.console.commands.ReadKeyCommand;
import ru.andrey.kvstorage.console.commands.UpdateKeyCommand;
import ru.andrey.kvstorage.exception.DatabaseException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DatabaseCommandsManager {

    private final Map<String, DatabaseCommand.DatabaseCommandBuilder> commandBuilders;

    public DatabaseCommandsManager() {
        this.commandBuilders = new HashMap<>();

        registerCommands();
    }

    public DatabaseCommandResult handleCommand(final ExecutionEnvironment env, final String... args) {
        final DatabaseCommand.DatabaseCommandBuilder builder = commandBuilders.get(args[0].toLowerCase());

        if (builder == null) {
            return DatabaseCommandResult.error(String.format("Command '%s' not found", args[0]));
        }

        try {
            final String[] nextArgs = Arrays.stream(args).skip(1).toArray(String[]::new);

            return DatabaseCommandResult.success(builder.build(env, nextArgs).execute());

        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }

    public void registerCommand(final String commandName, final DatabaseCommand.DatabaseCommandBuilder builder) {
        final String commandNameLower = commandName.toLowerCase();

        if (commandBuilders.containsKey(commandNameLower)) {
            throw new IllegalArgumentException(String.format("Command with the name '%s' is already exists", commandName));
        }

        commandBuilders.put(commandNameLower, builder);
    }

    private void registerCommands() {
        registerCommand("CREATE_DATABASE", CreateDatabaseCommand::fromArgs);
        registerCommand("CREATE_TABLE", CreateTableCommand::fromArgs);
        registerCommand("READ_KEY", ReadKeyCommand::fromArgs);
        registerCommand("UPDATE_KEY", UpdateKeyCommand::fromArgs);
    }
}
