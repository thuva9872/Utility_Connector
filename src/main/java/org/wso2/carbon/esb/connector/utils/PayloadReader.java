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

package org.wso2.carbon.esb.connector.utils;

import org.apache.axiom.om.OMElement;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.util.PayloadHelper;
import org.wso2.carbon.esb.connector.hmac.utils.exceptions.NoSuchContentTypeException;
import org.wso2.carbon.esb.connector.utils.constants.MIMETypes;

public class PayloadReader {
    private static final String payloadTypePropertyName = "messageType";

    public static String getPayload(MessageContext messageContext) throws NoSuchContentTypeException {

        org.apache.axis2.context.MessageContext axis2mc =
                ((Axis2MessageContext) messageContext).getAxis2MessageContext();
        String payloadType = (String) axis2mc.getProperty(payloadTypePropertyName);
        switch (payloadType) {
            case MIMETypes.application_json:
                return PayloadReader.getJSONPayload(messageContext);
            case MIMETypes.application_xml:
                return PayloadReader.getXMLPayload(messageContext);
            case MIMETypes.text_plain:
                return PayloadReader.getTextPayload(messageContext);
            default:
                throw new NoSuchContentTypeException("The content type " + payloadType + " is not defined.");
        }
    }

    public static String getTextPayload(MessageContext messageContext) {

        return PayloadHelper.getTextPayload(messageContext);
    }

    public static String getXMLPayload(MessageContext messageContext) {

        OMElement el = PayloadHelper.getXMLPayload(messageContext.getEnvelope());
        return el.toString();
    }

    public static String getJSONPayload(MessageContext messageContext) {

        return JsonUtil.jsonPayloadToString(((Axis2MessageContext) messageContext).getAxis2MessageContext());
    }
}
