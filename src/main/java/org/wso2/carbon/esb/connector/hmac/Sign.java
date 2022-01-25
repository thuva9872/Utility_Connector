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

package org.wso2.carbon.esb.connector.hmac;

import org.apache.synapse.MessageContext;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import org.wso2.carbon.esb.connector.hmac.utils.HMACGenerator;
import org.wso2.carbon.esb.connector.hmac.utils.constants.Constant;
import org.wso2.carbon.esb.connector.hmac.utils.exceptions.NoSuchContentTypeException;
import org.wso2.carbon.esb.connector.utils.PayloadReader;
import org.wso2.carbon.esb.connector.utils.PropertyReader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class Sign extends AbstractConnector {

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {
        log.info(messageContext.getEnvelope().getBody());
        Optional<String> payloadOptional = PropertyReader.getStringProperty(messageContext, "payload");
        Optional<String> algorithmOptional = PropertyReader.getStringProperty(messageContext, "algorithm");
        Optional<String> secretOptional = PropertyReader.getStringProperty(messageContext, "secret");
        Optional<String> saveToPropertyOptional = PropertyReader.getStringProperty(messageContext, "saveTo");

        String payload = "";
        try {
            payload = payloadOptional.orElse(PayloadReader.getPayload(messageContext));
        } catch (NoSuchContentTypeException e) {
            log.error("Invalid Content-Type: ", e);
        }

        String algorithm = algorithmOptional.orElse(Constant.defaultAlgorithm);
        String secret = secretOptional.orElse("");
        String saveToProperty = saveToPropertyOptional.orElse(Constant.saveSignResultTo);

        log.info("payload: " + payload);
        try {
            String sign = HMACGenerator.generateSignature(payload, secret, algorithm);
            messageContext.setProperty(saveToProperty, sign);
        } catch (NoSuchAlgorithmException e) {
            log.error("Invalid Algorithm: ", e);
        } catch (InvalidKeyException e) {
            log.error(e);
        }
    }
}