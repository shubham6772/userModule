package com.ecommerce.user.util;

import java.util.Base64;

public class Base64Util {
    public static String encodeNumber(int number) {
        String str = String.valueOf(number);
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    // Decode to number
    public static int decodeToNumber(String encoded) {
        byte[] decodedBytes = Base64.getDecoder().decode(encoded);
        String decodedString = new String(decodedBytes);
        return Integer.parseInt(decodedString);
    }
}
