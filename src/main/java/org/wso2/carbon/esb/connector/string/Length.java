package org.wso2.carbon.esb.connector.string;

import org.apache.commons.lang.StringUtils;
import org.apache.synapse.MessageContext;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import org.wso2.carbon.esb.connector.string.utils.constants.Constant;

import java.util.Optional;

import static org.wso2.carbon.esb.connector.utils.PropertyReader.getStringProperty;

public class Length extends AbstractConnector {

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {

        Optional<String> stringOptional = getStringProperty(messageContext, "string");
        Optional<String> saveToPropertyOptional = getStringProperty(messageContext, "saveTo");

        String string = stringOptional.orElse("");
        String saveTo = saveToPropertyOptional.orElse(Constant.SaveToPropertyLength);

        int length = StringUtils.length(string);

        messageContext.setProperty(saveTo, length);
    }
}
