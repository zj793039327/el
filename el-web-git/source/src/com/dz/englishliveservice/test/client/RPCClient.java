package com.dz.englishliveservice.test.client;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.dz.englishliveservice.test.bean.ExamPaper;


/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京紫光华宇软件股份有限公司<br>
 * 
 * @author zj
 * @version 1.0
 * @date 2013-9-6
 */
public class RPCClient
{
    public static void main(String[] args) throws Exception
    {
        helloWord();
        queryComplexPoJo();
    }

    public static void queryComplexPoJo() throws AxisFault
    {
        // call web service by RPC method
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();

        // specify URL for invoking
        EndpointReference targetEPR = new EndpointReference(
                "http://localhost:8080/el-mvn/services/examService");
        options.setTo(targetEPR);

        // specify parameter value for remote getGreeting method
        Object[] opAddEntryArgs = new Object[] {};

        // specify returned type for getGreeting method
        Class[] classes = new Class[]
        {ExamPaper.class };

        // specify method name for calling and WSDL namespace
        QName opAddEntry = new QName(
                "http://firststep.test.englishliveservice.dz.com",
                "loadOnePaper");
        // opAddEntry.equals(objectToTest);
        Object[] response = serviceClient.invokeBlocking(opAddEntry,
                opAddEntryArgs, classes);
        
        System.out.println(response[0]);

    }

    public static void helloWord() throws AxisFault
    {
        // call web service by RPC method
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();

        // specify URL for invoking
        EndpointReference targetEPR = new EndpointReference(
                "http://localhost:8080/el-mvn/services/myService");
        options.setTo(targetEPR);

        // specify parameter value for remote getGreeting method
        Object[] opAddEntryArgs = new Object[]
        { "eatToSomeThing" };

        // specify returned type for getGreeting method
        Class[] classes = new Class[]
        { String.class };

        // specify method name for calling and WSDL namespace
        QName opAddEntry = new QName(
                "http://firststep.test.englishliveservice.dz.com", "getPrice");
        // opAddEntry.equals(objectToTest);
        System.out.println(serviceClient.invokeBlocking(opAddEntry,
                opAddEntryArgs, classes)[0]);

    }

}
