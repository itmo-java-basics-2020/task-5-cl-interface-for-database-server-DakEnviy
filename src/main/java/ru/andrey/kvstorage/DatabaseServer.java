package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommandsManager;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

import java.util.Arrays;
import java.util.regex.Pattern;

public class DatabaseServer {

    private static final Pattern argRegexp = Pattern.compile("^[\\w-]+$");

    private final ExecutionEnvironment env;
    private final DatabaseCommandsManager commandsManager;

    public DatabaseServer(final ExecutionEnvironment env) {
        this.env = env;
        this.commandsManager = new DatabaseCommandsManager();
    }

    DatabaseCommandResult executeNextCommand(final String commandText) {
        if (commandText == null || commandText.isEmpty() || commandText.isBlank()) {
            return DatabaseCommandResult.error("Invalid command");
        }

        final String[] args = commandText.trim().split("\\s+");

        if (!Arrays.stream(args).allMatch(arg -> argRegexp.matcher(arg).matches())) {
            return DatabaseCommandResult.error("Invalid command");
        }

        return commandsManager.handleCommand(env, args);
    }
}
