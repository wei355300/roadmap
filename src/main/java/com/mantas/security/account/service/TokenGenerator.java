package com.mantas.security.account.service;

import java.util.UUID;

public class TokenGenerator {

    public static String randomToken() {
        return UUID.randomUUID().toString();
    }
}
