/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.esb.connector;

import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.util.PayloadHelper;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import org.wso2.carbon.connector.core.util.PayloadUtils;

/**
 * Sample method implementation.
 */
public class SampleConnector extends AbstractConnector {

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {

        Object templateParam1 = getParameter(messageContext, "param1");
        Object templateParam2 = getParameter(messageContext, "param2");
        log.info("Sample connector received message :" + templateParam1);
        SOAPEnvelope envelope = messageContext.getEnvelope();
        OMElement el = PayloadHelper.getXMLPayload(messageContext.getEnvelope());
        OMAttribute payload = el.getAttribute(PayloadHelper.TEXTELT);
        int sum = Integer.valueOf((String) templateParam1) + Integer.valueOf((String) templateParam2);
        try {
            log.info("Sample connector received message :" + sum);
            log.info("Envelope: " + envelope);
            log.info(messageContext);
            log.info("payload:" + el);
            PayloadUtils.preparePayload(((Axis2MessageContext) messageContext).getAxis2MessageContext(), "<result" +
                    "><success>" + sum + "</success></result>");
        } catch (Exception e) {
            throw new ConnectException(e);
        }
    }
}