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

package org.wso2.carbon.esb.connector.date;

import org.apache.synapse.MessageContext;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import org.wso2.carbon.esb.connector.date.utils.constants.Constant;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.wso2.carbon.esb.connector.utils.PropertyReader.getStringProperty;

public class GetDate extends AbstractConnector {

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {

        Optional<String> dateFormatOptional = getStringProperty(messageContext, "format");
        Optional<String> saveToPropertyOptional = getStringProperty(messageContext, "saveTo");
        String dateFormat = dateFormatOptional.orElse(Constant.dateFormat);
        String saveToProperty = saveToPropertyOptional.orElse(Constant.saveToProperty);

        try {
            Format formatter = new SimpleDateFormat(dateFormat);
            String date = formatter.format(new java.util.Date());
            messageContext.setProperty(saveToProperty, date);
        } catch (IllegalArgumentException exception) {
            log.error("Illegal format argument:", exception);
        }
    }
}
