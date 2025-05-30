package com.ecommerce.user.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class UniqueIDGenerator implements IdentifierGenerator {

    // Static utility method you can call anywhere:
    public static String generateUniqueId() {
        String raw = Long.toString(Math.abs(UUID.randomUUID().getMostSignificantBits()), 36);
        return raw.length() >= 8 ? raw.substring(0, 8).toLowerCase() : String.format("%-8s", raw).replace(' ', 'x').toLowerCase();
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return generateUniqueId();
    }
}
