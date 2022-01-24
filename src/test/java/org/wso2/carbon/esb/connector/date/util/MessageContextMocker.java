package org.wso2.carbon.esb.connector.date.util;

import org.apache.axiom.util.UIDGenerator;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.OperationContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.description.InOutAxisOperation;
import org.apache.synapse.MessageContext;
import org.apache.axis2.AxisFault;
import org.apache.synapse.core.axis2.MessageContextCreatorForAxis2;


public class MessageContextMocker {
    public static MessageContext getSynapseMessageContext() throws AxisFault {

        org.apache.axis2.context.MessageContext axis2MsgCtx = createAxis2MessageContext();
        ServiceContext svcCtx = new ServiceContext();
        OperationContext opCtx = new OperationContext(new InOutAxisOperation(), svcCtx);
        axis2MsgCtx.setServiceContext(svcCtx);
        axis2MsgCtx.setOperationContext(opCtx);
        axis2MsgCtx.setProperty("tenantDomain","carbon.super");

        return MessageContextCreatorForAxis2.getSynapseMessageContext(axis2MsgCtx);
    }

    private static org.apache.axis2.context.MessageContext createAxis2MessageContext() {

        org.apache.axis2.context.MessageContext axis2MsgCtx = new org.apache.axis2.context.MessageContext();
        axis2MsgCtx.setMessageID(UIDGenerator.generateURNString());
//        axis2MsgCtx.setConfigurationContext(
//                org.wso2.carbon.inbound.endpoint.osgi.service.ServiceReferenceHolder.getInstance()
//                        .getConfigurationContextService().getServerConfigContext());
        axis2MsgCtx.setProperty(org.apache.axis2.context.MessageContext.CLIENT_API_NON_BLOCKING, Boolean.TRUE);
        axis2MsgCtx.setServerSide(true);
        return axis2MsgCtx;
    }
}
