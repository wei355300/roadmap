package com.mantas.security.token;

import java.util.UUID;

public class TokenGenerator {

    public static String randomToken() {
        return UUID.randomUUID().toString();
    }
}
