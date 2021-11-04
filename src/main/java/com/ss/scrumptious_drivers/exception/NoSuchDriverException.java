package com.ss.scrumptious_drivers.exception;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * To be thrown when a Driver cannot be
 * found.
 *
 * <p>
 * Contains the offending ID that can be retrieved with {@link #getDriverId()}.
 */
public class NoSuchDriverException extends NoSuchElementException {

    private final UUID driverId;

    public NoSuchDriverException(UUID id) {
        super("No order record found for id=" + id);
        this.driverId = id;
    }

    public Optional<UUID> getDriverId() {
        return Optional.ofNullable(driverId);
    }

}