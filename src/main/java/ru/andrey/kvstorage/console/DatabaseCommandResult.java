package ru.andrey.kvstorage.console;

import lombok.AllArgsConstructor;

import java.util.Optional;

public interface DatabaseCommandResult {

    DatabaseCommandStatus getStatus();

    Optional<String> getResult();

    String getErrorMessage();

    static DatabaseCommandResult success(final String result) {
        return new Result(DatabaseCommandStatus.SUCCESS, result);
    }

    static DatabaseCommandResult error(final String message) {
        return new Result(DatabaseCommandStatus.FAILED, message);
    }

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    @AllArgsConstructor
    class Result implements DatabaseCommandResult {

        private final DatabaseCommandStatus status;
        private final String value;

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public Optional<String> getResult() {
            return status == DatabaseCommandStatus.SUCCESS
                ? Optional.ofNullable(value)
                : Optional.empty();
        }

        @Override
        public String getErrorMessage() {
            return status == DatabaseCommandStatus.FAILED ? value : null;
        }
    }
}