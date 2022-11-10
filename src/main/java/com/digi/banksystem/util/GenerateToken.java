package com.digi.banksystem.util;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateToken {

    public static String generateVerifyCode() {

        return RandomStringUtils.random(6, true, true);

    }

    public static String generateToken() {

        return RandomStringUtils.random(10, false, true);

    }

    public static String generateAccountNumber() {

        return RandomStringUtils.random(14, false, true);
    }
}
