package org.wso2.carbon.esb.connector.string;

import org.apache.synapse.MessageContext;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import org.wso2.carbon.esb.connector.string.utils.constants.Constant;

import java.util.Optional;

import static org.wso2.carbon.esb.connector.string.utils.CaseTransformer.transformToUpperCase;
import static org.wso2.carbon.esb.connector.utils.PropertyReader.getStringProperty;

public class TransformToUpperCase extends AbstractConnector {

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {

        Optional<String> stringOptional = getStringProperty(messageContext, "string");
        Optional<String> saveToPropertyOptional = getStringProperty(messageContext, "saveTo");

        String string = stringOptional.orElse("");
        String saveToProperty = saveToPropertyOptional.orElse(Constant.saveToPropertyCaseChanger);

        String transformedString = transformToUpperCase(string);
        messageContext.setProperty(saveToProperty, transformedString);
    }
}
