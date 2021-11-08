package com.ss.scrumptious_drivers.exception;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * To be thrown when an Delivery cannot be
 * found.
 *
 * <p>
 * Contains the offending ID that can be retrieved with {@link #getDeliveryId()}.
 */
public class NoSuchDeliveryException extends NoSuchElementException {

    private final Long deliveryId;

    public NoSuchDeliveryException(Long id) {
        super("No delivery record found for id=" + id);
        this.deliveryId = id;
    }

    public Optional<Long> getDeliveryId() {
        return Optional.ofNullable(deliveryId);
    }

}