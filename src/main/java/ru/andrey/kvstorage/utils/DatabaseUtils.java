package ru.andrey.kvstorage.utils;

import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

public final class DatabaseUtils {

    public static Database getDatabase(final ExecutionEnvironment env, final String databaseName) throws DatabaseException {
        return env.getDatabase(databaseName).orElseThrow(() ->
            new DatabaseException(String.format("Database with the name '%s' is not exist", databaseName))
        );
    }
}
