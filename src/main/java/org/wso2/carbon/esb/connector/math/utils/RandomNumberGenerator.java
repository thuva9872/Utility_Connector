package org.wso2.carbon.esb.connector.math.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator {

    private RandomNumberGenerator() {

    }

    //generate random integer
    public static int generateRandomInteger() {

        return ThreadLocalRandom.current().nextInt();
    }

    public static int generateRandomInteger(int bound) {

        return ThreadLocalRandom.current().nextInt(bound);
    }

    public static int generateRandomInteger(int origin, int bound) {

        return ThreadLocalRandom.current().nextInt(origin, bound);
    }
}
