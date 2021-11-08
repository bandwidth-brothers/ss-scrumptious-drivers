package com.ss.scrumptious_drivers.exception;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * To be thrown when an Order cannot be
 * found.
 *
 * <p>
 * Contains the offending ID that can be retrieved with {@link #getOrderId()}.
 */
public class NoSuchOrderException extends NoSuchElementException {

    private final Long orderId;

    public NoSuchOrderException(Long id) {
        super("No order record found for id=" + id);
        this.orderId = id;
    }

    public Optional<Long> getAddressId() {
        return Optional.ofNullable(orderId);
    }

}