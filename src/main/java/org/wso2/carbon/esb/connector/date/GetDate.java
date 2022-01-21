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
