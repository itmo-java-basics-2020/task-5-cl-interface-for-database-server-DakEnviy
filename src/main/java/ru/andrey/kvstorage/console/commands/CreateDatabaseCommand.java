package ru.andrey.kvstorage.console.commands;

import lombok.AllArgsConstructor;
import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

@AllArgsConstructor
public class CreateDatabaseCommand implements DatabaseCommand {

    private final ExecutionEnvironment env;
    private final String databaseName;

    @Override
    public String execute() throws DatabaseException {
        final Optional<Database> database = env.getDatabase(databaseName);

        if (database.isPresent()) {
            throw new DatabaseException(String.format("Database with the name '%s' is already exist", databaseName));
        }

        // TODO: add database to environment

        return null;
    }

    public static DatabaseCommand fromArgs(final ExecutionEnvironment env, final String... args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Invalid command usage. Usage: CREATE_DATABASE <db_name>");
        }

        return new CreateDatabaseCommand(env, args[0]);
    }
}
