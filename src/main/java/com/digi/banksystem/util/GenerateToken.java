package com.digi.banksystem.util;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateToken {

    public static String generateVerifyCode(){

        return RandomStringUtils.random(6, true,true);

    }
}
