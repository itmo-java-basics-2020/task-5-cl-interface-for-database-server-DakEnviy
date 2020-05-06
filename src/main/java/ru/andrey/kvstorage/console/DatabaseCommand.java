package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public interface DatabaseCommand {

    String execute() throws DatabaseException;

    @FunctionalInterface
    interface DatabaseCommandBuilder {

        DatabaseCommand build(ExecutionEnvironment env, String... args);
    }
}
