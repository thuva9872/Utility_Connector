package org.wso2.carbon.esb.connector.math;

import org.apache.synapse.MessageContext;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import org.wso2.carbon.esb.connector.math.utils.RandomNumberGenerator;
import org.wso2.carbon.esb.connector.math.utils.constants.Constant;

import java.util.Optional;

import static org.wso2.carbon.esb.connector.utils.PropertyReader.getIntProperty;
import static org.wso2.carbon.esb.connector.utils.PropertyReader.getStringProperty;

public class GetRandomNumber extends AbstractConnector {

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {

        Optional<Integer> originOptional = getIntProperty(messageContext, "origin");
        Optional<Integer> boundOptional = getIntProperty(messageContext, "bound");
        Optional<String> saveToPropertyOptional = getStringProperty(messageContext, "saveTo");
        String saveTo = saveToPropertyOptional.orElse(Constant.saveToProperty);
        log.info("read" + originOptional + " " + boundOptional);
        int origin = originOptional.orElse(Constant.intMin);
        int bound = boundOptional.orElse(Constant.intMax);
        int randomNumber = RandomNumberGenerator.generateRandomInteger(origin, bound);
        //        if(boundOptional.isPresent()){
        //            log.info("bound present");
        //            int bound= boundOptional.get();
        //            log.info("parsedInt bound");
        //            if(originOptional.isPresent()){
        //                log.info("origin present");
        //                int origin=originOptional.get();
        //                randomNumber= RandomNumberGenerator.generateRandomInteger(origin,bound);
        //            }
        //            else{
        //                randomNumber=RandomNumberGenerator.generateRandomInteger(bound);
        //            }
        //        }
        //        else{
        //            if(originOptional.isPresent()){
        //                int origin=originOptional.get();
        //                randomNumber=RandomNumberGenerator.generateRandomInteger(origin);
        //            }
        //            else{
        //                randomNumber = RandomNumberGenerator.generateRandomInteger();
        //            }
        //        }
        log.info("generated");
        messageContext.setProperty(saveTo, randomNumber);
    }
}
