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

package org.wso2.carbon.esb.connector.hmac.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMACGenerator {

    private static Map<String, Mac> macInstancesMap = new ConcurrentHashMap<>();

    public static String generateSignature(String payload, String secret, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {

        try {
            Mac mac = getMacInstance(algorithm);
            final SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), algorithm);
            mac.init(signingKey);
            return toHexString(mac.doFinal(payload.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("Invalid algorithm",e);
        }
        catch (InvalidKeyException e) {
            throw new InvalidKeyException("Invalid signingKey",e);
        }
        catch (NullPointerException e){
            throw new NullPointerException("Invalid secret");
        }
    }

    private static String toHexString(byte[] bytes) {

        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    private static Mac getMacInstance(String algorithm) throws NoSuchAlgorithmException {

        Mac macInstance = macInstancesMap.get(algorithm);
        if (macInstance == null) {
            macInstance = Mac.getInstance(algorithm);
            macInstancesMap.put(algorithm, macInstance);
        }
        return macInstance;
    }
}
