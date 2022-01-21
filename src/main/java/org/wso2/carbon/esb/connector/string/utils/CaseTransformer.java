package org.wso2.carbon.esb.connector.string.utils;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.esb.connector.string.utils.constants.Constant;

public class CaseTransformer {

    private CaseTransformer() {

    }

    public static String transformToLowerCase(String string) {

        String transformedString = StringUtils.lowerCase(string, Constant.caseChangerLocale);
        return transformedString;
    }

    public static String transformToUpperCase(String string) {

        String transformedString = StringUtils.upperCase(string, Constant.caseChangerLocale);
        return transformedString;
    }
}
