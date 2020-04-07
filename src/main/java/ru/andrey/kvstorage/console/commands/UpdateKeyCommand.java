package ru.andrey.kvstorage.console.commands;

import lombok.AllArgsConstructor;
import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;
import ru.andrey.kvstorage.utils.DatabaseUtils;

@AllArgsConstructor
public class UpdateKeyCommand implements DatabaseCommand {

    private final ExecutionEnvironment env;
    private final String databaseName;
    private final String tableName;
    private final String key;
    private final String value;

    @Override
    public String execute() throws DatabaseException {
        final Database database = DatabaseUtils.getDatabase(env, databaseName);

        database.write(tableName, key, value);

        return null;
    }

    public static DatabaseCommand fromArgs(final ExecutionEnvironment env, final String... args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid database name. Usage: READ_KEY <db_name> <table_name> <key> <value>");
        }

        return new UpdateKeyCommand(env, args[0], args[1], args[2], args[3]);
    }
}
