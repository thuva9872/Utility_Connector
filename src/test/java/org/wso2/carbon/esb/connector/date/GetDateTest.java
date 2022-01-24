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

import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wso2.carbon.esb.connector.date.util.MessageContextMocker;
import org.wso2.carbon.esb.connector.utils.PropertyReader;

import java.text.Format;
import java.text.SimpleDateFormat;


@ExtendWith(MockitoExtension.class)
class GetDateTest {

    @Mock
    private MessageContext messageContext;

    @Test
    void testMediate_noTargetPropertyNoDateFormat_currentDateInDateProperty() throws AxisFault {

        GetDate getDate = new GetDate();
        messageContext = MessageContextMocker.getSynapseMessageContext();
        messageContext.setProperty("format", "HH:mm:ss");
        //        try(MockedStatic<PropertyReader> mockedPR= Mockito.mockStatic(PropertyReader.class)){
        //            mockedPR.when(()->PropertyReader.getStringProperty(messageContext,"format")).thenReturn
        //            (Optional.empty());
        //            mockedPR.when(()->PropertyReader.getStringProperty(messageContext,"saveTo")).thenReturn
        //            (Optional.empty());
        //            mockedPR.when(()->PropertyReader.getStringProperty(messageContext,"date")).thenCallRealMethod();
        //            try {
        //                getDate.connect(messageContext);
        //            } catch (ConnectException e) {
        //                e.printStackTrace();
        ////            }
        //            final String expectedProperty="date";
        //            Format formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //            final String expectedDate = formatter.format(new java.util.Date());
        //            Assertions.assertEquals(PropertyReader.getStringProperty(messageContext,"date"),expectedDate);
        //        }
        final String expectedProperty = "date";
        Format formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final String expectedDate = formatter.format(new java.util.Date());
        Assertions.assertEquals(PropertyReader.getStringProperty(messageContext, "date"), expectedDate);
    }
}
