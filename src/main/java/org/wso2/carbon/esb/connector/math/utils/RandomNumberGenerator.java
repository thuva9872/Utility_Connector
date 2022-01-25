/*
 *
 *  * Copyright (c) 2022, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *  *
 *  * WSO2 Inc. licenses this file to you under the Apache License,
 *  * Version 2.0 (the "License"); you may not use this file except
 *  * in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing,
 *  * software distributed under the License is distributed on an
 *  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  * KIND, either express or implied. See the License for the
 *  * specific language governing permissions and limitations
 *  * under the License.
 *
 */

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
        try{
            return ThreadLocalRandom.current().nextInt(origin, bound);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Lower bound > Upper bound",e);
        }
    }
}
