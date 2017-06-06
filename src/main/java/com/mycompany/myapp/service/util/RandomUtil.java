package com.mycompany.myapp.service.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * Utility class for generating random Strings.
 */
public final class RandomUtil {

    private static final int DEF_COUNT = 20;

    private RandomUtil() {
    }

    /**
     * Generates a password.
     *
     * @return the generated password
     */
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    /**
     * Generates an activation key.
     *
     * @return the generated activation key
     */
    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

    /**
     * Generates a reset key.
     *
     * @return the generated reset key
     */
    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }


    /**
     * Generates a String number between.
     *
     * @return the generated nr_inregistrare
     */
    public static String generateNrInregistrare() {
        Random r = new Random();
        final int Low = 1000;
        final int High = 10000;
        int result = r.nextInt(High - Low) + Low;
        return "" + result;
    }
}
