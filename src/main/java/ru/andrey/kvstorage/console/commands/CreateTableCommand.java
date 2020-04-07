package ru.andrey.kvstorage.console.commands;

import lombok.AllArgsConstructor;
import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;
import ru.andrey.kvstorage.utils.DatabaseUtils;

@AllArgsConstructor
public class CreateTableCommand implements DatabaseCommand {

    private final ExecutionEnvironment env;
    private final String databaseName;
    private final String tableName;

    @Override
    public String execute() throws DatabaseException {
        final Database database = DatabaseUtils.getDatabase(env, databaseName);

        database.createTableIfNotExists(tableName);

        return null;
    }

    public static DatabaseCommand fromArgs(final ExecutionEnvironment env, final String... args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid database name. Usage: CREATE_TABLE <db_name> <table_name>");
        }

        return new CreateTableCommand(env, args[0], args[1]);
    }
}
