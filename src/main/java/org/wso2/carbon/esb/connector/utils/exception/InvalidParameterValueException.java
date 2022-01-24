package org.wso2.carbon.esb.connector.utils.exception;

public class InvalidParameterValueException extends Exception{

    public InvalidParameterValueException(String message){
        super(message);
    }

    public InvalidParameterValueException(String message,Throwable cause){
        super(message,cause);
    }
}
