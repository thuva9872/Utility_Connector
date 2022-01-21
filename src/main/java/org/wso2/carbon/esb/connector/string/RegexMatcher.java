package org.wso2.carbon.esb.connector.string;

import org.apache.synapse.MessageContext;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import org.wso2.carbon.esb.connector.string.utils.constants.Constant;

import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static org.wso2.carbon.esb.connector.utils.PropertyReader.getStringProperty;

public class RegexMatcher extends AbstractConnector {

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {

        Optional<String> inputOptional = getStringProperty(messageContext, "string");
        Optional<String> regexOptional = getStringProperty(messageContext, "regex");
        Optional<String> saveToPropertyOptional = getStringProperty(messageContext, "saveTo");

        String input = inputOptional.orElse("");
        String regex = regexOptional.orElse("");
        String saveToProperty = saveToPropertyOptional.orElse(Constant.saveToPropertyRegexMatcher);

        try {
            Boolean matching = Pattern.matches(regex, input);
            messageContext.setProperty(saveToProperty, matching.toString());
        } catch (PatternSyntaxException e) {
            log.error("Invalid regular expression:",e);
        }

    }
}

