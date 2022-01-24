package org.wso2.carbon.esb.connector.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.synapse.MessageContext;
import org.wso2.carbon.connector.core.util.ConnectorUtils;
import org.wso2.carbon.esb.connector.utils.exception.InvalidParameterValueException;

import java.util.Optional;

public class PropertyReader {

    private PropertyReader() {

    }

    /**
     * Read a String parameter
     *
     * @param mc           SimpleMessageContext.
     * @param parameterKey Key of the parameter.
     * @return Optional String of the parameter value.
     */
    public static Optional<String> getStringProperty(MessageContext mc, String parameterKey) {

        String parameter = (String) ConnectorUtils.lookupTemplateParamater(mc, parameterKey);
        if (StringUtils.isNotBlank(parameter)) {
            return Optional.of(parameter);
        }
        return Optional.empty();
    }

    public static Optional<Integer> getIntProperty(MessageContext mc, String parameterKey) {

        Optional<String> parameter = getStringProperty(mc, parameterKey);
        return parameter.map(s -> {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    public static Optional<String> getEnumProperty(MessageContext mc,String parameterKey,Class<Enum> enumType) throws InvalidParameterValueException {
        Optional<String> property = getStringProperty(mc,parameterKey);
        if(property.isPresent()){
            try{
                Optional<String> result=Optional.of( Enum.valueOf(enumType,property.get()).toString());
                return result;
            }
            catch (Exception e){
                throw new InvalidParameterValueException("Invalid Parameter Value: ",e);
            }
        }
        else{
            return Optional.empty();
        }
    }
}
