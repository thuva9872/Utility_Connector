package org.wso2.carbon.esb.connector.string;

import org.apache.synapse.MessageContext;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import org.wso2.carbon.esb.connector.string.utils.constants.Constant;

import java.util.Optional;
import java.util.UUID;

import static org.wso2.carbon.esb.connector.utils.PropertyReader.getStringProperty;

public class RandomUUID extends AbstractConnector {

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {

        Optional<String> saveToPropertyOptional = getStringProperty(messageContext, "saveTo");
        String saveToProperty = saveToPropertyOptional.orElse(Constant.saveToPropertyUUID);

        UUID uuid = UUID.randomUUID();

        messageContext.setProperty(saveToProperty, uuid.toString());
    }
}
